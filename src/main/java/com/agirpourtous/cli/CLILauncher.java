package com.agirpourtous.cli;

import com.agirpourtous.Main;
import com.agirpourtous.cli.menus.MainMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.gui.controllers.ConnexionController;
import javafx.stage.Stage;

public class CLILauncher {
    private APIClient client;

    public void start() {
        System.out.println("Start in CLI mode");
        client = new APIClient();
        new MainMenu(client);
    }

    public void stop() throws Exception {

        client.close();
    }
}
