package com.agirpourtous.gui.plugin;

import com.agirpourtous.Main;
import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.elements.Element;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.pf4j.ExtensionPoint;

import java.io.IOException;

public abstract class GuiPluginController implements ExtensionPoint {
    @FXML
    Pane root;
    @FXML
    Label name;
    @FXML
    ImageView icon;
    PluginMenuController controller;
    Pane parent;

    public void display(PluginMenuController controller, Pane parent) throws IOException {
        this.controller = controller;
        this.parent = parent;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/elements/plugin_element.fxml"));
        loader.setController(this);
        root = loader.load();
        parent.getChildren().add(root);
    }

    public abstract void start();

    public void onClick(MouseEvent mouseEvent){
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
                start();
            }
        }
    }
}
