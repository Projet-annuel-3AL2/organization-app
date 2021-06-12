package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.forms.AddUserForm;
import com.agirpourtous.core.api.APIClient;

public class UsersManagementMenu extends Menu {
    protected static final int id = 0;

    public UsersManagementMenu(CLILauncher launcher) {
        super(launcher, "Menu des utilisateurs");

        addAction(new Action("Afficher les utilisateurs") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new UserSelectionMenu(launcher));
            }
        });

        addAction(new Action("Ajouter un utilisateur") {
            @Override
            public void execute() {
                launcher.getClient().getUserService()
                        .create(new AddUserForm().askEntries())
                        .doOnTerminate(() -> launcher.setActiveMenu(new MainMenu(launcher)))
                        .block();
            }
        });
    }
}
