package com.agirpourtous.cli.menus;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.User;

import java.util.stream.Collectors;

public class UserSelectionMenu extends Menu {

    UserSelectionMenu(APIClient client) {
        super("Menu de selection d'utilisateur");
        client.getUserService()
                .findAll()
                .collect(Collectors.toList())
                .subscribe(users -> {
                    if (users == null || users.size() <= 0) {
                        new MainMenu(client);
                        return;
                    }
                    for (User user : users) {
                        if (user.getId().equals(client.getUser().getId())) {
                            continue;
                        }
                        addAction(new Action(user.getUsername()) {
                            @Override
                            public void execute() {
                                new UserMenu(client, user);
                            }
                        });
                    }
                    addAction(new Action("Retour au menu principal") {
                        @Override
                        public void execute() {
                            new MainMenu(client);
                        }
                    });
                    start();
                });
        start();
    }
}
