package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.controller.ConnectionController;
import com.agirpourtous.core.api.APIClient;

public class MainMenu extends Menu {

    public MainMenu(APIClient client) {
        super("Main Menu");

        addAction(new Action("Connect") {
            @Override
            public void execute() {
                ConnectionController connectionController = new ConnectionController();
                if (connectionController.start(client)){
                    new HomePageMenu(client);
                }
            }
        });

    }
}
