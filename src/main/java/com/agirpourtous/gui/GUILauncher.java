package com.agirpourtous.gui;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.gui.controllers.ConnexionController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GUILauncher extends Application {
    private static final Logger logger = LoggerFactory.getLogger(GUILauncher.class.getName());
    private APIClient client;

    @Override
    public void start(Stage stage) {
        logger.info("Start in GUI mode");
        client = new APIClient();
        new ConnexionController(client);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        client.close();
    }
}
