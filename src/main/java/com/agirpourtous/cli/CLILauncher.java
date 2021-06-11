package com.agirpourtous.cli;

import com.agirpourtous.cli.menus.AuthMenu;
import com.agirpourtous.core.api.APIClient;

public class CLILauncher {
    private APIClient client;

    public void start() {
        System.out.println("Start in CLI mode");
        new AuthMenu();
    }

    public void stop()  {

        client.close();
    }
}
