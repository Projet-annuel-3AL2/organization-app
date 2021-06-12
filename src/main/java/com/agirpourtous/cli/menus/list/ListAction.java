package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.menus.Action;
import com.agirpourtous.core.models.Entity;

public abstract class ListAction extends Action {
    public ListAction(String displayAction) {
        super(displayAction);
    }

    public abstract Entity getEntity();

    @Override
    public void execute() {

    }
}
