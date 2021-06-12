package com.agirpourtous.cli.menus;

import com.agirpourtous.core.api.APIClient;

public class MainMenu extends Menu {

    public MainMenu(APIClient client) {
        super("Menu principal");

        /*addAction(new Action("Menu de gestion des projets") {
            @Override
            public void execute() {
                new ProjectsManagementMenu(client);
            }
        });*/

        addAction(new Action("Menu de gestion d'utilisateurs") {
            @Override
            public void execute() {
                new UsersManagementMenu(client);
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
