package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.api.APIClient;

public class MainMenu extends Menu {

    public MainMenu(APIClient client) {
        super("Main Menu");
        client = new APIClient();
        APIClient finalClient = client;

        addAction(new Action("Connect") {
            @Override
            public void execute() {
                ConnectionMenu connectionMenu = new ConnectionMenu();
                connectionMenu.start(finalClient);
            }
        });

    }
}
