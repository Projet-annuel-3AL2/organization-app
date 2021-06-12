package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.forms.AddTicketForm;
import com.agirpourtous.cli.menus.list.ProjectTicketsListMenu;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;

public class ProjectMenu extends Menu {
    protected static final int id = 0;

    public ProjectMenu(CLILauncher launcher, Project project) {
        super(launcher, "Menu du projet " + project.getName());
        addAction(new Action("Afficher les tickets") {
            @Override
            public void execute() {
                Ticket ticket = (Ticket) new ProjectTicketsListMenu(launcher,project).startList();
                if(ticket == null){
                    return;
                }
                launcher.setActiveMenu(new TicketMenu(launcher, ticket));
            }
        });
        addAction(new Action("Ajouter un ticket") {
            @Override
            public void execute() {
                launcher.getClient().getProjectService()
                        .addTicket(project.getId(), new AddTicketForm().askEntries())
                        .doOnTerminate(() -> start())
                        .subscribe();
            }
        });
        if (launcher.getClient().getUser().isAdmin()) {
            addAction(new Action("Gestion d'utilisateurs") {
                @Override
                public void execute() {
                    launcher.setActiveMenu(new ProjectUsersMenu(launcher, project));
                }
            });
            addAction(new Action("Supprimer le projet") {
                @Override
                public void execute() {
                    launcher.getClient().getProjectService()
                            .delete(project.getId())
                            .doOnError(err -> launcher.setActiveMenu(new MainMenu(launcher)))
                            .doOnTerminate(() -> launcher.setActiveMenu(new MainMenu(launcher)))
                            .subscribe();
                }
            });
        }
        addAction(new Action("Retour au menu principal") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new MainMenu(launcher));
            }
        });
    }
}
