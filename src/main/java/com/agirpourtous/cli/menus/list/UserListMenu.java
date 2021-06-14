package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserListMenu extends ListSelectionMenu {

    public UserListMenu(CLILauncher launcher) {
        super(launcher, "Sélectionnez un utilisateur");
    }

    @Override
    protected void loadEntityList() {
        try {
            List<User> users = getUsers();
            for (User user : users) {
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
        } catch (Exception ignored) {
        }
    }

    private List<User> getUsers() {
        return launcher.getClient().getUserService()
                .findAll()
                .collect(Collectors.toList())
                .block();
    }
}
