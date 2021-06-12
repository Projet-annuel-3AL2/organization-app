package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.menus.Menu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;

import java.util.ArrayList;

public abstract class ListSelectionMenu extends Menu {
    protected final ArrayList<ListAction> actions;
    protected final APIClient client;

    public ListSelectionMenu(APIClient client) {
        super("");
        this.client = client;
        this.actions = new ArrayList<>();
        addAction(new ListAction("Retour au menu principal") {
            @Override
            public Entity getEntity() {
                return null;
            }
        });
    }

    public Entity startList() {
        if (actions.size() <= 0) {
            System.out.println("Il n'y a pour le moment aucun élément à sélectionner");
            return null;
        }
        return actions.get(getChoice()).getEntity();
    }

    protected abstract void loadEntityList();
}
