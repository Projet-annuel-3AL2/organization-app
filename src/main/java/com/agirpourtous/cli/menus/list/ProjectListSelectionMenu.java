package com.agirpourtous.cli.menus.list;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;

import java.util.stream.Collectors;

public class ProjectListSelectionMenu extends ListSelectionMenu {

    public ProjectListSelectionMenu(APIClient client) {
        super(client);
    }

    @Override
    protected void loadEntityList() {
        client.getUserService()
                .getProjects()
                .collect(Collectors.toList())
                .subscribe(projects -> {
                    for (Project project : projects) {
                        actions.add(new ListAction(project.getName()) {
                            @Override
                            public Entity getEntity() {
                                return project;
                            }
                        });
                    }
                });
    }
}
