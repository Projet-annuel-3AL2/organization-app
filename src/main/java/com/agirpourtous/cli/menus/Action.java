package com.agirpourtous.cli.menus;

public abstract class Action {
    private final String displayAction;

    Action(String displayAction) {
        this.displayAction = displayAction;
    }

    public abstract void execute();

    public String getDisplayAction() {
        return displayAction;
    }
}
