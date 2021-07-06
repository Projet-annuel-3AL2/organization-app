package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.Menu;

public abstract class ListSelectionMenu extends Menu {

    public ListSelectionMenu(CLILauncher launcher, String display) {
        super(launcher, display);
    }

    public void addAction(ListAction action) {
        actions.add(action);
    }

    public void initActions() {
        loadEntityList();
        addAction(new ListAction("Retour au menu principal") {
            @Override
            public Object getEntity() {
                return null;
            }
        });
    }

    public Object startList() {
        initActions();
        if (actions.size() <= 1) {
            System.out.println("Il n'y a pour le moment aucun élément à sélectionner");
            return null;
        }
        return ((ListAction) actions.get(getChoice())).getEntity();
    }

    protected abstract void loadEntityList();
}
