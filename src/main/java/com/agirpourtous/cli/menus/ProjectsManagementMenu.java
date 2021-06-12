package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.menus.forms.AddProjectForm;
import com.agirpourtous.core.api.APIClient;

public class ProjectsManagementMenu extends Menu {
    public ProjectsManagementMenu(APIClient client) {
        super("Projects menu");
        addAction(new Action("Choisir un projet") {
            @Override
            public void execute() {
                new ProjectSelectionMenu(client);
            }
        });
        if (client.getUser().isAdmin()) {
            addAction(new Action("Ajouter un projet") {
                @Override
                public void execute() {
                    client.getProjectService()
                            .create(new AddProjectForm().askEntries())
                            .doOnSuccess(project -> new ProjectMenu(client, project))
                            .doOnError(err -> new MainMenu(client))
                            .block();
                }
            });
        }
        start();
    }
}
