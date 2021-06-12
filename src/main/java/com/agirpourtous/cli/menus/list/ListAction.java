package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.menus.Action;

public abstract class ListAction extends Action {
    public ListAction(String displayAction) {
        super(displayAction);
    }

    public abstract Object getEntity();

    @Override
    public void execute() {

    }
}
