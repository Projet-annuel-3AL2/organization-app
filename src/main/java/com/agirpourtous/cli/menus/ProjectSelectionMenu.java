package com.agirpourtous.cli.menus;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;

import java.util.stream.Collectors;

public class ProjectSelectionMenu extends Menu {
    public ProjectSelectionMenu(APIClient client) {
        super("Selectionnez un projet:");
        for (Project project : client.getUserService()
                .getProjects()
                .collect(Collectors.toList())
                .block()) {
            addAction(new Action(project.getName()) {
                @Override
                public void execute() {
                    new ProjectMenu(client, project);
                }
            });
            addAction(new Action("Retour au menu principal") {
                @Override
                public void execute() {
                    new HomePageMenu(client);
                }
            });
        }
    }
}
