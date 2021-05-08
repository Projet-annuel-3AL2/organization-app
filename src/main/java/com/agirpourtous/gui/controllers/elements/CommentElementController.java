package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.core.models.Comment;
import com.agirpourtous.gui.controllers.Controller;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CommentElementController extends Element {
    private Comment comment;
    public Label authorLabel;
    public Label commentLabel;

    CommentElementController(Controller controller, Pane parent, Comment comment) throws IOException {
        super("elements/comment_element", controller, parent);
        this.comment = comment;
        updateLabels();
    }

    private void updateLabels(){
        commentLabel.setText(comment.getText());
        //authorLabel.setText(comment.getUser().getId());
    }
    protected void updateComment(Comment comment){
        this.comment = comment;
        updateLabels();
    }
}
