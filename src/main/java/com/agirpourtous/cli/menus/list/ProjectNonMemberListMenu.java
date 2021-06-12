package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.stream.Collectors;

public class ProjectNonMemberListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectNonMemberListMenu(CLILauncher launcher, Project project) {
        super(launcher, "Sélectionnez un utilisateur ne faisant pas partie du projet " + project.getName());
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        launcher.getClient().getUserService()
                .findAll()
                .collect(Collectors.toList())
                .subscribe(users -> launcher.getClient().getProjectService()
                        .getMembers(project.getId())
                        .collect(Collectors.toList())
                        .subscribe(members -> {
                            users.removeAll(members);
                            for (User user : users) {
                                addAction(new ListAction(user.getUsername()) {
                                    @Override
                                    public Entity getEntity() {
                                        return user;
                                    }
                                });

                            }
                        }));
    }
}
