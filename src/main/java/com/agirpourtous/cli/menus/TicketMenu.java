package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.list.TicketStatusListMenu;
import com.agirpourtous.core.api.requests.AddTicketRequest;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.TicketStatus;

public class TicketMenu extends Menu {
    public TicketMenu(CLILauncher launcher, Ticket ticket) {
        super(launcher, "Menu du ticket " + ticket.getTitle());

        addAction(new Action("Changer le status du ticket") {
            @Override
            public void execute() {
                AddTicketRequest addTicketRequest = new AddTicketRequest();
                addTicketRequest.setStatus((TicketStatus) new TicketStatusListMenu(launcher).startList());
                launcher.getClient()
                        .getTicketService()
                        .update(ticket.getId(), addTicketRequest)
                        .subscribe();
            }
        });
        addAction(new Action("Gérer les commentaires") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new MainMenu(launcher));
            }
        });
        addAction(new Action("Editer le ticket") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new MainMenu(launcher));
            }
        });
        addAction(new Action("Supprimer le ticket") {
            @Override
            public void execute() {
                launcher.getClient()
                        .getTicketService()
                        .delete(ticket.getId())
                        .subscribe();
                launcher.setActiveMenu(new MainMenu(launcher));
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
