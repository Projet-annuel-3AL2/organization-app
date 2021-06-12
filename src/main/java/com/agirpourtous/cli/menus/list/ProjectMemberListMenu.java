package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.stream.Collectors;

public class ProjectMemberListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectMemberListMenu(CLILauncher launcher, Project project) {
        super(launcher);
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        launcher.getClient().getProjectService()
                .getMembers(project.getId())
                .collect(Collectors.toList())
                .doOnSuccess(members -> {
                    for (User user : members) {
                        actions.add(new ListAction(user.getUsername()) {
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
