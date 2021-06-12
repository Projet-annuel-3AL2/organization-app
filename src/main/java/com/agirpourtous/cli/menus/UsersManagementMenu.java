package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.forms.AddUserForm;
import com.agirpourtous.cli.menus.list.UserListMenu;
import com.agirpourtous.core.models.User;

public class UsersManagementMenu extends Menu {
    protected static final int id = 0;

    public UsersManagementMenu(CLILauncher launcher) {
        super(launcher, "Menu des utilisateurs");

        addAction(new Action("Afficher les utilisateurs") {
            @Override
            public void execute() {
                User user = (User) new UserListMenu(launcher).startList();
                if (user != null) {
                    launcher.setActiveMenu(new UserMenu(launcher, user));
                } else {
                    launcher.setActiveMenu(new MainMenu(launcher));
                }
            }
        });

        addAction(new Action("Ajouter un utilisateur") {
            @Override
            public void execute() {
                launcher.getClient().getUserService()
                        .create(new AddUserForm().askEntries())
                        .doOnTerminate(() -> launcher.setActiveMenu(new MainMenu(launcher)))
                        .subscribe();
            }
        });
        addAction(new Action("Retour au menu principal") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new MainMenu(launcher));
            }
        });
    }
}
