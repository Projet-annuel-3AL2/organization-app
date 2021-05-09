package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.User;
import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.ProjectDetailsController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TicketElementController extends Element {
    public VBox ticketElement;
    public Label titleLabel;
    public Label descriptionLabel;
    public Label priorityLabel;
    public Label assigneeLabel;
    private Ticket ticket;

    public TicketElementController(Controller controller, Pane parent, Ticket ticket) throws IOException {
        super("elements/ticket_element", controller, parent);
        this.ticket = ticket;
        setLabels();
    }

    private void setLabels() {
        titleLabel.setText(ticket.getTitle());
        descriptionLabel.setText(ticket.getDescription());
        priorityLabel.setText("Priority: " + ticket.getPriority());
        User assignee = controller.getClient()
                .getUserService()
                .findById(ticket.getAssigneeId())
                .block();
        if (assignee != null) {
            assigneeLabel.setText(assignee.getUsername());
        }
    }

    public void updateTicket(Ticket ticket) {
        this.ticket = ticket;
        setLabels();
    }

    public void onTicketElementClick() {
        ((ProjectDetailsController) controller).displayTicketDetails(ticket);
    }
}
