package com.agirpourtous.cli.controller;

import com.agirpourtous.cli.menus.TicketMenu;
import com.agirpourtous.cli.menus.UserMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.User;

import java.util.Scanner;

public class UserController {
    private final static Scanner SCANNER = new Scanner(System.in);

    // TODO : affcicher list of Users
    public void findAllUser(APIClient client) {
        System.out.println("List of all User :");

        client.getUserService().findAll();

        new UserMenu(client);
    }

    public void findUserById(APIClient client) {
        System.out.println("Insert id of the user you want to display : ");

        String userId = null;
        while (userId == null) {
            userId = SCANNER.next();
        }

        try {
            client.getUserService().findById(userId);
        }catch (Error error){
            System.out.println("An Error Occur with");
        }

        new UserMenu(client);
    }

    public void createUser(APIClient client) {
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

        System.out.println("Input boolean (true or false) or nothing for default false");
        isAdmin = SCANNER.nextBoolean();
        if (isAdmin != true && isAdmin != false ){
            isAdmin = false;
        }

        // TODO :  create User with var
        User newUser = new User();

        try {
            client.getUserService().create(newUser);

        }catch (Error error){
            System.out.println("Error");
        }

        new UserMenu(client);
    }

    public void setANewAdmin(APIClient client) {

    }

    public void updateUser(APIClient client) {
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

        // TODO : get User with id given by user
        client.getUserService().findById(userId);
        User user = new User();

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

        // TODO : create User object with given var
        User newUser = new User();

        try {
            client.getUserService().create(newUser);

        }catch (Error error){
            System.out.println("Error");
        }

        new UserMenu(client);
    }

    public void deleteUSer(APIClient client) {
        System.out.println("Insert id of the User you want to remove : ");

        String userId = null;
        while (userId == null) {
            userId = SCANNER.next();
        }

        try {
            client.getUserService().delete(userId);
        }catch (Error error){
            System.out.println("An Error Occur with");
        }

        new UserMenu(client);
    }
}
