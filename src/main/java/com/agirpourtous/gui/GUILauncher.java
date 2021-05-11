package com.agirpourtous.gui;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.gui.controllers.ConnexionController;
import javafx.application.Application;
import javafx.stage.Stage;


public class GUILauncher extends Application {
    private APIClient client;

    @Override
    public void start(Stage stage) {
        System.out.println("Start in GUI mode");
        client = new APIClient();
        new ConnexionController(client);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        client.close();
    }
}
