package com.agirpourtous.gui.plugin;

import com.agirpourtous.Main;
import com.agirpourtous.gui.controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.pf4j.ExtensionPoint;

import java.io.IOException;

public abstract class GuiPluginController implements ExtensionPoint {
    private final Image image;
    private final String name;
    @FXML
    Pane root;
    @FXML
    Label nameLabel;
    @FXML
    ImageView imageView;
    Controller controller;
    Pane parent;

    public GuiPluginController(Image image, String name) {
        this.image = image;
        this.name = name;
    }

    public void display(Controller controller, Pane parent) throws IOException {
        this.controller = controller;
        this.parent = parent;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/elements/plugin_element.fxml"));
        loader.setController(this);
        root = loader.load();
        parent.getChildren().add(root);
        this.imageView.setImage(image);
        this.nameLabel.setText(name);
    }

    public abstract void start();

    public void onClick(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                start();
            }
        }
    }

    public Controller getController() {
        return controller;
    }
}
