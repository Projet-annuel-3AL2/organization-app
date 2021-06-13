package com.agirpourtous.gui;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.gui.controllers.ConnexionController;
import com.agirpourtous.gui.controllers.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;


public class GUILauncher extends Application {
    private static final Logger logger = LoggerFactory.getLogger(GUILauncher.class.getName());
    private APIClient client;

    @Override
    public void start(Stage stage) {
        logger.info("Start in GUI mode");
        client = new APIClient();
        if (Files.exists(Path.of("cookie.bin"))) {
            new MainMenuController(client);
        } else {
            new ConnexionController(client);
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        client.close();
    }
}
