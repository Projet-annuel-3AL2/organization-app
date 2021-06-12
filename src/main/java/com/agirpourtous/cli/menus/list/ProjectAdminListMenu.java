package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectAdminListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectAdminListMenu(CLILauncher launcher, Project project) {
        super(launcher, "Sélectionnez un administrateur");
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        try {
            List<User> admins = getAdmins();
            for (User user : admins) {
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

    private List<User> getAdmins() {
        return launcher.getClient().getProjectService()
                .getAdmins(project.getId())
                .collect(Collectors.toList())
                .block();
    }
}
