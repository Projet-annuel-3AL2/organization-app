package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectMemberListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectMemberListMenu(CLILauncher launcher, Project project) {
        super(launcher, "Sélectionnez un membre du projet " + project.getName());
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        try {
            List<User> members = getMembers();
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
}
