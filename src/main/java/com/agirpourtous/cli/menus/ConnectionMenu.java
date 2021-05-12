package com.agirpourtous.cli.menus;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.api.services.AuthService;

import java.util.Scanner;

public class ConnectionMenu {
    private LoginRequest loginRequest;
    private final static Scanner SCANNER = new Scanner(System.in);

    public void start(APIClient client) {
        boolean loopBreak = false;
        do {
            System.out.println("Insert your Username : ");

            String username = null;
            while (username == null) {
                username = SCANNER.next();
            }

            System.out.println("Insert your Password : ");

            String password = null;
            while (password == null) {
                password = SCANNER.next();
            }

            loginRequest = new LoginRequest(username, password);

            if (client.connect(loginRequest)){
                loopBreak = true;
            }else{
                System.out.println("Username/Password aren't in dataBase");
            }
        }while (!loopBreak );

        System.out.println("Je suis connecté");

    }


}
