package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.User;

import java.util.stream.Collectors;

public class UserListSelectionMenu extends ListSelectionMenu {

    public UserListSelectionMenu(CLILauncher launcher) {
        super(launcher, "Sélectionnez un utilisateur");
    }

    @Override
    protected void loadEntityList() {
        launcher.getClient().getUserService()
                .findAll()
                .collect(Collectors.toList())
                .doOnError(Throwable::printStackTrace)
                .doOnSuccess(projects -> {
                    for (User user : projects) {
                        if (user.getId().equals(launcher.getClient().getUser().getId())) {
                            continue;
                        }
                        addAction(new ListAction(user.getUsername()) {
                            @Override
                            public Entity getEntity() {
                                return user;
                            }
                        });
                    }
                })
                .block();
    }
}
