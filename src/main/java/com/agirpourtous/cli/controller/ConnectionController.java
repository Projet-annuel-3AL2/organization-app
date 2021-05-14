package com.agirpourtous.cli.controller;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.api.services.AuthService;

import java.util.Scanner;

public class ConnectionController {
    private LoginRequest loginRequest;
    private final static Scanner SCANNER = new Scanner(System.in);

    public boolean start(APIClient client) {
        boolean loopBreack = false;

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

            try{
                if (client.connect(loginRequest)){
                    loopBreack = true;
                }
            }catch (Exception e){
                System.out.println("Username/Password aren't in dataBase");
            }

        }while (!loopBreack);

        System.out.println("You are connected ");
        return true;
    }


}
