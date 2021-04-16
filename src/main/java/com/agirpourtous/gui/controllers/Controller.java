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

    Controller(String fxml, APIClient client) {
        this.client = client;
        this.stage = new Stage();
        this.stage.setResizable(false);
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/" + fxml + ".fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Organization app - Main menu");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    Controller(String fxml, APIClient client, Stage stage) {
        this.client = client;
        this.stage = stage;
        this.stage.setResizable(false);
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/" + fxml + ".fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Organization app - Main menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
