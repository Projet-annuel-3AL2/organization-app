package com.agirpourtous.cli.menus.list;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.stream.Collectors;

public class ProjectAdminListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectAdminListMenu(APIClient client, Project project) {
        super(client);
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        client.getProjectService()
                .getAdmins(project.getId())
                .collect(Collectors.toList())
                .subscribe(members -> {
                    for (User user : members) {
                        actions.add(new ListAction(user.getUsername()) {
                            @Override
                            public Entity getEntity() {
                                return user;
                            }
                        });

                    }
                });
    }
}
