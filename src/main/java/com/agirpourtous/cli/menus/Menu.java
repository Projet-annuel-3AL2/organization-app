package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import org.pf4j.ExtensionPoint;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu implements ExtensionPoint {
    protected final Scanner SCANNER = new Scanner(System.in);
    protected CLILauncher launcher;
    protected final ArrayList<Action> actions;
    private final String menuName;

    public Menu(CLILauncher launcher, String menuName) {
        this.launcher = launcher;
        this.menuName = menuName;
        actions = new ArrayList<>();
    }

    public Menu(String menuName) {
        this.menuName = menuName;
        actions = new ArrayList<>();
    }

    private void display() {
        StringBuilder display = new StringBuilder();
        display.append(menuName).append(":\n");
        for (int i = 0; i < actions.size(); i++) {
            display.append(i).append(". ").append(actions.get(i).getDisplayAction()).append("\n");
        }
        display.append("->");
        System.out.println(display);
    }

    public void start() {
        if (actions.size() <= 0) {
            return;
        }
        actions.get(getChoice()).execute();
    }

    protected int getChoice() {
        int choice = -1;
        while (choice < 0 || choice >= actions.size()) {
            display();
            while (!SCANNER.hasNextInt()) {
                display();
                SCANNER.next();
            }
            choice = SCANNER.nextInt();
        }
        return choice;
    }

    public Menu pluginBuild(CLILauncher launcher) {
        this.launcher = launcher;
        return this;
    }

    public void addAction(Action action) {
        actions.add(action);
    }
}
