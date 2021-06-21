package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.list.PluginListMenu;

public class MainMenu extends Menu {

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
        addAction(new Action("Lancer un plugin") {
            @Override
            public void execute() {
                Menu menu = (Menu) new PluginListMenu(launcher).startList();
                if (menu != null) {
                    launcher.setActiveMenu(menu.pluginBuild(launcher));
                }
            }
        });
        addAction(new Action("Déconnecter") {
            @Override
            public void execute() {
                launcher.getClient().logout();
                launcher.setActiveMenu(new AuthMenu(launcher));
            }
        });
    }
}
