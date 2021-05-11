package com.agirpourtous.cli.menus;

public class MainMenu extends Menu {
    MainMenu() {
        super("Main Menu");
        addAction(new Action("Se connecter") {
            @Override
            public void execute() {
                //ConnectionMenu connectionMenu = new ConnectionMenu()
                //connectionMenu.start();
            }
        });
    }
}
