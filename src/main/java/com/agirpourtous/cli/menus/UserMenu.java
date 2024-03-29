package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.User;

public class UserMenu extends Menu {

    UserMenu(CLILauncher launcher, User user) {
        super(launcher, "Menu de l'utilisateur " + user.getUsername());
        String adminRight;
        if (!user.isAdmin()) {
            adminRight = "Ajouter les droits d'administrations";
        } else {
            adminRight = "Retirer les droits d'administrations";
        }
        addAction(new Action(adminRight) {
            @Override
            public void execute() {
                launcher.getClient().getUserService()
                        .setAdmin(user)
                        .subscribe();
            }
        });
        addAction(new Action("Supprimer l'utilisateur") {
            @Override
            public void execute() {
                launcher.getClient().getUserService()
                        .delete(user.getId())
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
