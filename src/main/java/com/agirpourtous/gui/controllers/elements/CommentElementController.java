package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.gui.controllers.Controller;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CommentElementController extends Element {
    public Label authorLabel;
    public Label commentLabel;

    CommentElementController(Controller controller, Pane parent) throws IOException {
        super("elements/comment_element", controller, parent);
    }
}
