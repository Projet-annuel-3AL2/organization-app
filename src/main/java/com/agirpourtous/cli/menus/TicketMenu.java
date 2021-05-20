package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.controller.TicketController;
import com.agirpourtous.core.api.APIClient;

public class TicketMenu extends Menu {

    private final TicketController ticketController = new TicketController();

    public TicketMenu(APIClient client) {
        super("Ticket Menu");

        addAction(new Action("find all") {
            @Override
            public void execute() {
                ticketController.getAllTicket(client);
            }
        });

        addAction(new Action("Find ticket by Id Ticket") {
            @Override
            public void execute() {
                ticketController.getTicketById(client);
            }
        });

        addAction(new Action("Delete with Id Ticket") {
            @Override
            public void execute() {
                ticketController.removeTicketWithId(client);
            }
        });

        addAction(new Action("update a Ticket") {
            @Override
            public void execute() {
                ticketController.updateTicket(client);
            }
        });

        addAction(new Action("Set an Assigne to a Ticket") {
            @Override
            public void execute() {
                ticketController.setAssigneeWithIdUserAndIdTicket(client);
            }
        });

        addAction(new Action("Get comment of an ticket") {
            @Override
            public void execute() {
                ticketController.getCommentOfTicket(client);
            }
        });

        addAction(new Action("Add a Comment to a ticket") {
            @Override
            public void execute() {
                ticketController.addCommentToTicket(client);
            }
        });

        addAction(new Action("Return HomePage") {
            @Override
            public void execute() {
                new HomePageMenu(client);
            }
        });

        start();
    }
}
