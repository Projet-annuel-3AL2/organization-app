package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.menus.forms.AddTicketForm;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;

public class ProjectMenu extends Menu {
    public ProjectMenu(APIClient client, Project project) {
        super("Menu du projet " + project.getName());
        addAction(new Action("Afficher les tickets") {
            @Override
            public void execute() {
                new TicketSelectionMenu(client);
            }
        });
        addAction(new Action("Ajouter un ticket") {
            @Override
            public void execute() {
                client.getProjectService()
                        .addTicket(project.getId(), new AddTicketForm().askEntries())
                        .doOnTerminate(() -> start())
                        .block();
            }
        });
        if (client.getUser().isAdmin()) {
            addAction(new Action("Gestion d'utilisateurs") {
                @Override
                public void execute() {
                    new ProjectUsersMenu(client, project);
                }
            });
            addAction(new Action("Supprimer le projet") {
                @Override
                public void execute() {
                    client.getProjectService()
                            .delete(project.getId())
                            .doOnTerminate(() -> new MainMenu(client))
                            .block();
                }
            });
        }
        start();
    }
}
