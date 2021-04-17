package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.Main;
import com.agirpourtous.gui.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public abstract class Element {
    protected Controller controller;
    private final Pane parent;

    public Element(String fxml, Controller controller, Pane parent) throws IOException {
        this.controller = controller;
        this.parent = parent;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/" + fxml + ".fxml"));
        loader.setController(this);
        Pane rootPane = loader.load();
        parent.getChildren().add(rootPane);
    }
}
