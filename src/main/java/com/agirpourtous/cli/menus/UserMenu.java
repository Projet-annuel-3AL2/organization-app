package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.controller.UserController;
import com.agirpourtous.core.api.APIClient;

public class UserMenu  extends Menu{

    private UserController userController = new UserController();

    public UserMenu(APIClient client) {
        super("User Menu");

        addAction(new Action("Find all Users") {
            @Override
            public void execute() {
                userController.findAllUser(client);
            }
        });

        addAction(new Action("Find User with Id") {
            @Override
            public void execute() {
                userController.findUserById(client);
            }
        });


        addAction(new Action("update an User") {
            @Override
            public void execute() {
                userController.updateUser(client);
            }
        });

        addAction(new Action("Delete") {
            @Override
            public void execute() {
                userController.deleteUSer(client);
            }
        });

        if (client.getUser().isAdmin()){

            addAction(new Action("Create User") {
                @Override
                public void execute() {
                    userController.createUser(client);
                }
            });

            addAction(new Action("Set an Admin") {
                @Override
                public void execute() {
                    userController.setANewAdmin(client);
                }
            });
        }

        addAction(new Action("Return HomePage") {
            @Override
            public void execute() {
                new HomePageMenu(client);
            }
        });

        start();
    }
}
