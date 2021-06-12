package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;

public class MainMenu extends Menu {
    protected static final int id = 1;

    public MainMenu(CLILauncher launcher) {
        super(launcher, "Menu principal");
        addAction(new Action("Menu de gestion des projets") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new ProjectsManagementMenu(launcher));
            }
        });
        addAction(new Action("Menu de gestion d'utilisateurs") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new UsersManagementMenu(launcher));
            }
        });
        addAction(new Action("Disconnect") {
            @Override
            public void execute() {
                launcher.getClient().logout();
                launcher.setActiveMenu(new AuthMenu(launcher));
            }
        });
    }
}
