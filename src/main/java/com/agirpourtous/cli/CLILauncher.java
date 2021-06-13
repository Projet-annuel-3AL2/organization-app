package com.agirpourtous.cli;

import com.agirpourtous.cli.menus.AuthMenu;
import com.agirpourtous.cli.menus.MainMenu;
import com.agirpourtous.cli.menus.Menu;
import com.agirpourtous.core.api.APIClient;

import java.nio.file.Files;
import java.nio.file.Path;

public class CLILauncher {
    private final APIClient client;
    private Menu activeMenu;

    public CLILauncher() {
        this.client = new APIClient();
        if (Files.exists(Path.of("cookie.bin"))) {
            this.activeMenu = new MainMenu(this);
        } else {
            this.activeMenu = new AuthMenu(this);
        }
    }

    public void start() {
        System.out.println("Start in CLI mode");
        while (true) {
            activeMenu.start();
        }
    }

    public APIClient getClient() {
        return client;
    }

    public Menu getActiveMenu() {
        return activeMenu;
    }

    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }
}
