package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.User;
import com.agirpourtous.gui.controllers.Controller;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CommentElement extends Element {
    public Label authorLabel;
    public Label commentLabel;
    private Comment comment;

    CommentElement(Controller controller, Pane parent, Comment comment) throws IOException {
        super("comment_element", controller, parent);
        this.comment = comment;
        commentLabel.setPrefWidth(100);
        updateLabels();
    }

    private void updateLabels() {
        commentLabel.setText("comment.getText()\n aaaaa");
        System.out.println(commentLabel.getText());
        User user = controller.getClient().getUserService().findById(comment.getUserId()).block();
        assert user != null;
        authorLabel.setText(user.getUsername());
    }

    protected void updateComment(Comment comment) {
        this.comment = comment;
        updateLabels();
    }
}
