package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.menus.forms.AddUserForm;
import com.agirpourtous.core.api.APIClient;

public class UsersManagementMenu extends Menu {
    public UsersManagementMenu(APIClient client) {
        super("Menu des utilisateurs");

        addAction(new Action("Afficher les utilisateurs") {
            @Override
            public void execute() {
                new UserSelectionMenu(client);
            }
        });

        addAction(new Action("Ajouter un utilisateur") {
            @Override
            public void execute() {
                client.getUserService()
                        .create(new AddUserForm().askEntries())
                        .doOnTerminate(() -> new MainMenu(client))
                        .block();
            }
        });

        start();
    }
}
