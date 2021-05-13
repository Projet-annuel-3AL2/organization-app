package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.controller.ConnectionController;
import com.agirpourtous.core.api.APIClient;

public class AuthMenu extends Menu {

    public AuthMenu(APIClient client) {
        super("Main Menu");

        addAction(new Action("AuthMenu") {
            @Override
            public void execute() {
                ConnectionController connectionController = new ConnectionController();
                if (connectionController.start(client)){
                    new HomePageMenu(client);
                }
            }
        });

        // TODO: A supprimer
        addAction(new Action("HomePage") {
            @Override
            public void execute() {
                new HomePageMenu(client);
            }
        });

        start();
    }
}