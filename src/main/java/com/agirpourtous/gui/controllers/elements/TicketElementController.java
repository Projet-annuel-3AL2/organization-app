package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.ProjectDetailsController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TicketElementController extends Element {
    private Ticket ticket;
    public VBox ticketElement;
    public Label titleLabel;
    public Label descriptionLabel;
    public Label priorityLabel;
    public Label assigneeLabel;

    public TicketElementController(Controller controller, Pane parent, Ticket ticket) throws IOException {
        super("elements/ticket_element", controller, parent);
        this.ticket = ticket;
        titleLabel.setText(ticket.getTitle());
        descriptionLabel.setText(ticket.getDescription());
        priorityLabel.setText("Priority: " + ticket.getPriority());
        //assigneeLabel.setText(ticket.getAssignee().getUsername());
    }

    public void updateTicket(Ticket ticket){
        this.ticket = ticket;
    }
    public void onTicketElementClick() {
        ((ProjectDetailsController) controller).displayTicketDetails(ticket);
    }
}
