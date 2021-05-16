package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.core.api.requests.AddCommentRequest;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.User;
import com.agirpourtous.gui.controllers.Controller;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import reactor.retry.Repeat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TicketDetailsElement extends Element {
    private final Ticket ticket;
    private final HashMap<String, CommentElement> comments;
    private final SimpleDateFormat formatter;
    public VBox ticketDetails;
    public Label titleLabel;
    public Label creationDateLabel;
    public Label priorityLabel;
    public Label assigneeLabel;
    public Label creatorLabel;
    public Label descriptionLabel;
    public VBox commentsVBox;
    public TextArea commentTextArea;
    public Button addCommentButton;
    public Button closeButton;

    public TicketDetailsElement(Controller controller, Pane parent, Ticket ticket) throws IOException {
        super("ticket_details", controller, parent);
        this.ticket = ticket;
        formatter = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
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
        creationDateLabel.setText(formatter.format(ticket.getCreationDate()));
        priorityLabel.setText(String.valueOf(ticket.getPriority()));
        descriptionLabel.setText(ticket.getDescription());
        User assignee = controller.getClient()
                .getUserService()
                .findById(ticket.getAssigneeId())
                .block();
        if (assignee != null) {
            assigneeLabel.setText(assignee.getUsername());
        }
        User creator = controller.getClient()
                .getUserService()
                .findById(ticket.getCreatorId())
                .block();
        if (creator != null) {
            creatorLabel.setText(creator.getUsername());
        }
    }

    public void addComment(Comment comment) {
        if (!comments.containsKey(comment.getId())) {
            try {
                comments.put(comment.getId(), new CommentElement(controller, commentsVBox, comment));
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
        controller.getClient()
                .getTicketService()
                .addComment(ticket.getId(), new AddCommentRequest(commentTextArea.getText()))
                .subscribe();
    }

    public void onCloseClick() {
        close();
    }

    public void close() {
        parent.getChildren().remove(ticketDetails);
        isActive = false;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
