package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.menus.forms.AddProjectForm;
import com.agirpourtous.cli.menus.list.ProjectListSelectionMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;

public class ProjectsManagementMenu extends Menu {
    public ProjectsManagementMenu(APIClient client) {
        super("Projects menu");
        addAction(new Action("Choisir un projet") {
            @Override
            public void execute() {
                Project project = (Project) new ProjectListSelectionMenu(client).startList();
                if (project != null) {
                    new ProjectMenu(client, project);
                } else {
                    new MainMenu(client);
                }
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
                            .subscribe();
                }
            });
        }
        start();
    }
}
