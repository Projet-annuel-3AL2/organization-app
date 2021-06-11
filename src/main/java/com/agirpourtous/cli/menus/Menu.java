package com.agirpourtous.cli.menus;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final String menuName;
    private final ArrayList<Action> actions;

    Menu(String menuName) {
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

    private int getChoice() {
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

    public void addAction(Action action) {
        actions.add(action);
    }
}
