package com.agirpourtous.cli.controller;

import com.agirpourtous.cli.menus.UserMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.AddUserRequest;
import com.agirpourtous.core.models.User;

import java.io.IOException;
import java.util.Scanner;

public class UserController {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Show show = new Show();


    public void findAllUser(APIClient client) {
        System.out.println("List of all User :");

        client.getUserService().findAll().subscribe(show::showUser);
        new UserMenu(client);
    }

    public void findUserById(APIClient client) {
        System.out.println("Insert id of the user you want to display : ");

        String userId = null;
        while (userId == null) {
            userId = SCANNER.next();
        }
        try{
            User user = client.getUserService().findById(userId).block();
            show.showUser(user);
        }catch (Exception e){
            System.out.println("There is no user with this id");
        }


        new UserMenu(client);
    }

    public void createUser(APIClient client) {
        if (client.getUser().isAdmin()){

            String username = null;
            String lastname = null;
            String firstname = null;
            String mail = null;
            boolean isAdmin = false;

            System.out.println("Input a Username :");
            while (username == null){
                username = SCANNER.next();
            }

            System.out.println("Input a Lastname :");
            while (lastname == null){
                lastname = SCANNER.next();
            }

            System.out.println("Input a firstname :");
            while (firstname == null){
                firstname = SCANNER.next();
            }

            System.out.println("Input a mail :");
            while (mail == null){
                mail = SCANNER.next();
            }


            AddUserRequest addUserRequest = new AddUserRequest(username,mail,firstname,lastname, isAdmin);

            try {
                client.getUserService().create(addUserRequest).block();
            }catch (Exception e){
                System.out.println("Error while creating New User");
            }
        }else{
            System.out.println("You are not admin");
        }

        new UserMenu(client);
    }

    public void setANewAdmin(APIClient client) {
        if (client.getUser().isAdmin()){
            String userId = null;
            System.out.println("Input id of user you want to grant admin");

            while (userId == null){
                userId = SCANNER.next();
            }
            try {
                User user = client.getUserService().findById(userId).block();
                if (user != null){
                    client.getUserService().setAdmin(user);
                }else{
                    System.out.println("There is no user with id : " + userId);
                }
            }catch (Exception e){
                System.out.println("There is no user with id : " + userId);
            }
        }else{
            System.out.println("You are not admin");
        }

        new UserMenu(client);
    }

    public void updateUser(APIClient client) {
        if (client.getUser().isAdmin()){

            String userId = null;
            String username = null;
            String lastname = null;
            String firstname = null;
            String mail = null;
            boolean isAdmin = false;

            System.out.println("Insert id of User you want to update :");
            while(userId == null){
                userId = SCANNER.next();
            }

            try {
                User user = client.getUserService().findById(userId).block();
                if (user != null){

                    System.out.println("Insert username (enter to keep " + user.getUsername() +" ) : ");
                    username = SCANNER.next();
                    if (username == null){
                        username = user.getUsername();
                    }

                    System.out.println("Insert lastname (enter to keep " + user.getLastname() +" ) : ");
                    lastname = SCANNER.next();
                    if (lastname == null){
                        lastname = user.getLastname();
                    }

                    System.out.println("Insert firstname (enter to keep " + user.getFirstname() +" ) : ");
                    firstname = SCANNER.next();
                    if (firstname == null){
                        firstname = user.getFirstname();
                    }

                    System.out.println("Insert mail (enter to keep " + user.getMail() +" ) : ");
                    mail = SCANNER.next();
                    if (mail == null){
                        mail = user.getMail();
                    }

                    System.out.println("Insert mail (isAdmin = " + user.isAdmin() +" ) : ");
                    isAdmin = SCANNER.nextBoolean();

                    AddUserRequest addUserRequest = new AddUserRequest(username,mail,firstname,lastname, isAdmin);

                    try {
                        client.getUserService().create(addUserRequest).block();
                    }catch (Exception e){
                        System.out.println("Error while creating new user");
                    }
                }
            }catch (Exception e){
                System.out.println("There is no User with the given Id");
            }
        }else{
            System.out.println("You are not admin");
        }

        new UserMenu(client);
    }

    public void deleteUSer(APIClient client) {
        if (client.getUser().isAdmin()){
            System.out.println("Insert id of the User you want to remove : ");

            String userId = null;
            while (userId == null) {
                userId = SCANNER.next();
            }

            try {
                client.getUserService().delete(userId);
            }catch (Exception e){
                System.out.println("There is no user with the given id");
            }
        }else{
            System.out.println("You are not admin");
        }

        new UserMenu(client);
    }
}
