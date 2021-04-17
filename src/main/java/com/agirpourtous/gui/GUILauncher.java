package com.agirpourtous.gui;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.gui.controllers.ConnexionController;
import javafx.application.Application;
import javafx.stage.Stage;


public class GUILauncher extends Application {
    private static APIClient client;

    public static void setClient(APIClient client) {
        GUILauncher.client = client;
    }

    @Override
    public void start(Stage stage) {
        System.out.println("Start in GUI mode");
        new ConnexionController(client);
    }
}
