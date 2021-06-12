package com.agirpourtous.cli.menus;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.User;

public class UserMenu extends Menu {
    UserMenu(APIClient client, User user) {
        super("Menu de l'utilisateur " + user.getUsername());
        String adminRight;
        if (!user.isAdmin()) {
            adminRight = "Ajouter les droits d'administrations";
        } else {
            adminRight = "Retirer les droits d'administrations";
        }
        addAction(new Action(adminRight) {
            @Override
            public void execute() {
                client.getUserService()
                        .setAdmin(user)
                        .block();
            }
        });
        addAction(new Action("Supprimer l'utilisateur") {
            @Override
            public void execute() {
                client.getUserService()
                        .delete(user.getId())
                        .doOnTerminate(() -> new MainMenu(client))
                        .block();
            }
        });
        start();
    }
}
