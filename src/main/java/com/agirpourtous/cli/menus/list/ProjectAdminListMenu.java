package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.stream.Collectors;

public class ProjectAdminListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectAdminListMenu(CLILauncher launcher, Project project) {
        super(launcher,"Sélectionnez un administrateur");
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        launcher.getClient().getProjectService()
                .getAdmins(project.getId())
                .collect(Collectors.toList())
                .doOnSuccess(members -> {
                    for (User user : members) {
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
