package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.gui.controllers.Controller;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import reactor.retry.Repeat;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TicketDetailsController extends Element {
    private final Ticket ticket;
    private final HashMap<String, CommentElementController> comments;
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
        this.comments = new HashMap<>();
        setLabels();
        controller.getClient().getTicketService()
                .getComment(ticket.getId())
                .collect(Collectors.toList())
                .repeatWhen(Repeat.onlyIf(repeatContext -> isActive)
                        .fixedBackoff(Duration.ofSeconds(10)))
                .subscribe(comments -> Platform.runLater(() -> setProjects(comments)));
    }


    private void setLabels() {
        titleLabel.setText(ticket.getTitle());
        creationDateLabel.setText(ticket.getCreationDate().toString());
        priorityLabel.setText(String.valueOf(ticket.getPriority()));
        descriptionLabel.setText(ticket.getDescription());
    }

    public void addComment(Comment comment) {
        if (!comments.containsKey(comment.getId())) {
            try {
                comments.put(comment.getId(), new CommentElementController(controller, commentsVBox, comment));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            comments.get(comment.getId()).updateComment(comment);
        }
    }

    private void removeComment(String id) {
        comments.get(id).remove();
        comments.remove(id);
    }

    private void removeDeletedProjects(List<Comment> commentList) {
        List<String> receivedIds = commentList.stream().map(Comment::getId).collect(Collectors.toList());
        List<String> elementsDeleted = new ArrayList<>(comments.keySet());
        elementsDeleted.removeAll(receivedIds);
        elementsDeleted.forEach(this::removeComment);
    }

    private void setProjects(List<Comment> commentList) {
        removeDeletedProjects(commentList);

        commentList.forEach(this::addComment);
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
