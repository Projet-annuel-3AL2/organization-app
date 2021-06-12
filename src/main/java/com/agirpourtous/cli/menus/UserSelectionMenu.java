package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.User;

import java.util.stream.Collectors;

public class UserSelectionMenu extends Menu {
    protected static final int id = 0;

    UserSelectionMenu(CLILauncher launcher) {
        super(launcher, "Menu de selection d'utilisateur");
        launcher.getClient().getUserService()
                .findAll()
                .collect(Collectors.toList())
                .doOnError(res -> launcher.setActiveMenu(new MainMenu(launcher)))
                .subscribe(users -> {
                    if (users == null || users.size() <= 1) {
                        launcher.setActiveMenu(new MainMenu(launcher));
                        return;
                    }
                    for (User user : users) {
                        if (user.getId().equals(launcher.getClient().getUser().getId())) {
                            continue;
                        }
                        addAction(new Action(user.getUsername()) {
                            @Override
                            public void execute() {
                                launcher.setActiveMenu(new UserMenu(launcher, user));
                            }
                        });
                    }
                    addAction(new Action("Retour au menu principal") {
                        @Override
                        public void execute() {
                            launcher.setActiveMenu(new MainMenu(launcher));
                        }
                    });
                });
    }
}
