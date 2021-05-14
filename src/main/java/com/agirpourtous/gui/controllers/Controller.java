package com.agirpourtous.gui.controllers;

import com.agirpourtous.Main;
import com.agirpourtous.core.api.APIClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {
    protected final Stage stage;
    protected final APIClient client;
    protected boolean isActive;

    Controller(String fxml, APIClient client) {
        this.isActive = true;
        this.client = client;
        this.stage = new Stage();
        this.stage.setMinWidth(800);
        this.stage.setMinHeight(600);
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/" + fxml + ".fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Organization app");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Controller(String fxml, Controller previousController) {
        this.isActive = true;
        this.client = previousController.getClient();
        this.stage = previousController.getStage();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/" + fxml + ".fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public APIClient getClient() {
        return client;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
