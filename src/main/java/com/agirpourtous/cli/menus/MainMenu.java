package com.agirpourtous.cli.menus;

import com.agirpourtous.core.api.APIClient;

public class MainMenu extends Menu {

    public MainMenu(APIClient client) {
        super("Menu principal");

        addAction(new Action("Project Menu") {
            @Override
            public void execute() {
                new ProjectsMenu(client);
            }
        });

        addAction(new Action("User Menu") {
            @Override
            public void execute() {
                new UserMenu(client);
            }
        });

        addAction(new Action("Disconnect") {
            @Override
            public void execute() {
                client.close();
                new AuthMenu();
            }
        });

        start();
    }
}
