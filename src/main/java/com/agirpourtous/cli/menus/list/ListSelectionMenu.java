package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.Menu;
import com.agirpourtous.core.models.Entity;

public abstract class ListSelectionMenu extends Menu {

    public ListSelectionMenu(CLILauncher launcher, String display) {
        super(launcher, display);
        loadEntityList();
        addAction(new ListAction("Retour au menu principal") {
            @Override
            public Entity getEntity() {
                return null;
            }
        });
    }

    public void addAction(ListAction action) {
        actions.add(action);
    }

    public Entity startList() {
        if (actions.size() <= 1) {
            System.out.println("Il n'y a pour le moment aucun élément à sélectionner");
            return null;
        }
        return ((ListAction) actions.get(getChoice())).getEntity();
    }

    protected abstract void loadEntityList();
}
