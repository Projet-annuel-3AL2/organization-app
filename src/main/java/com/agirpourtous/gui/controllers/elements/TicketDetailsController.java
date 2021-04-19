package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.gui.controllers.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TicketDetailsController extends Element {
    private final Ticket ticket;
    public VBox ticketDetails;
    public Label titleLabel;
    public Label creationDateLabel;
    public Label priorityLabel;
    public Label assigneeLabel;
    public Label creatorLabel;
    public Label descriptionLabel;
    public VBox commentsVBox;
    public TextField commentTextField;
    public Button addCommentButton;
    public Button closeButton;

    public TicketDetailsController(Controller controller, Pane parent, Ticket ticket) throws IOException {
        super("elements/ticket_details", controller, parent);
        this.ticket = ticket;
        ticket.getComments().forEach(this::addComment);
    }

    public void addComment(Comment comment) {
        try {
            new CommentElementController(controller, commentsVBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAddCommentClick() {
    }

    public void onCloseClick() {
        close();
    }

    public void close() {
        parent.getChildren().remove(ticketDetails);
    }
}
