package com.agirpourtous.gui.plugin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.pf4j.ExtensionPoint;

public abstract class GuiPluginController implements ExtensionPoint {
    @FXML
    Label name;
    @FXML
    ImageView icon;

    public abstract void start();

    public void onClick(MouseEvent mouseEvent){
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
                start();
            }
        }
    }
}
