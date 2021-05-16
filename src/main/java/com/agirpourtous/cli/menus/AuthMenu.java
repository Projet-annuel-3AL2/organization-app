package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.controller.ConnectionController;
import com.agirpourtous.core.api.APIClient;

public class AuthMenu extends Menu {

    public AuthMenu(APIClient client) {
        super("Auth Menu");

        addAction(new Action("Auth") {
            @Override
            public void execute() {
                ConnectionController connectionController = new ConnectionController();
                if (connectionController.start(client)){
                    new HomePageMenu(client);
                }else{
                    new AuthMenu(client);
                }
            }
        });

        start();
    }
}
