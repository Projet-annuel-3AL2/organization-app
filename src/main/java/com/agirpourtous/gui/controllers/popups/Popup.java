package com.agirpourtous.gui.controllers.popups;

import com.agirpourtous.Main;
import com.agirpourtous.gui.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Popup {
    protected boolean isActive;
    protected Controller controller;
    protected Stage stage;

    public Popup(String title, String fxml, Controller controller) throws IOException {
        this.isActive = true;
        this.controller = controller;
        this.stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/popups/" + fxml + ".fxml"));
        loader.setController(this);
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void close() {
        isActive = false;
        stage.close();
    }

    public Controller getController() {
        return controller;
    }
}
