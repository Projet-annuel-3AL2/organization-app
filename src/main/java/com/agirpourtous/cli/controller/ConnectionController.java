package com.agirpourtous.cli.controller;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.LoginRequest;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionController {
    private final static Scanner SCANNER = new Scanner(System.in);
    private LoginRequest loginRequest;

    public boolean start(APIClient client) {
        AtomicBoolean loopBreack = new AtomicBoolean(false);

        do {
            System.out.println("Insert your Username : ");

            String username = null;
            String password = null;

            while (username == null) {
                username = SCANNER.next();
            }

            System.out.println("Insert your Password : ");


            while (password == null) {
                password = SCANNER.next();
            }

            loginRequest = new LoginRequest(username, password);

            client.connect(loginRequest).doOnSuccess(response ->{
                loopBreack.set(true);
            }).doOnError(response ->{
                System.out.println("Username/Password aren't in dataBase");
            }).block() ;

        } while (!loopBreack.get());

        System.out.println("You are connected ");
        return true;
    }


}
