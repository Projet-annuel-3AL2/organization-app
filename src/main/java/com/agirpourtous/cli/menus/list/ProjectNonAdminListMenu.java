package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectNonAdminListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectNonAdminListMenu(CLILauncher launcher, Project project) {
        super(launcher, "Sélectionnez un membre n'ayant pas les droits d'administration");
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        try {
            List<User> members = getMembers();
            List<User> admins = getAdmins();
            members.removeAll(admins);
            for (User user : members) {
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

    private List<User> getMembers() {
        return launcher.getClient().getProjectService()
                .getMembers(project.getId())
                .collect(Collectors.toList())
                .block();
    }

    private List<User> getAdmins() {
        return launcher.getClient().getProjectService()
                .getAdmins(project.getId())
                .collect(Collectors.toList())
                .block();
    }
}
