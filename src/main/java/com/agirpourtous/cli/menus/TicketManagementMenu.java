package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.forms.AddTicketForm;
import com.agirpourtous.cli.menus.list.ProjectTicketsListMenu;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;

public class TicketManagementMenu extends Menu {

    public TicketManagementMenu(CLILauncher launcher, Project project) {
        super(launcher, "Gestion des tickets du projet " + project.getName());
        addAction(new Action("Choisir un tickets") {
            @Override
            public void execute() {
                Ticket ticket = (Ticket) new ProjectTicketsListMenu(launcher, project).startList();
                if (ticket == null) {
                    return;
                }
                launcher.setActiveMenu(new TicketMenu(launcher, project, ticket));
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
        addAction(new Action("Retour au menu principal") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new MainMenu(launcher));
            }
        });
    }
}
