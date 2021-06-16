package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.Menu;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;

import java.nio.file.Path;

public class PluginListMenu extends ListSelectionMenu {
    private final PluginManager pluginManager;

    public PluginListMenu(CLILauncher launcher) {
        super(launcher, "Sélectionnez un projet");
        pluginManager = new DefaultPluginManager(Path.of("./plugins"));
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
    }

    @Override
    protected void loadEntityList() {
        for (Menu menu : pluginManager.getExtensions(Menu.class)) {
            addAction(new ListAction(pluginManager.whichPlugin(menu.getClass()).getPluginId()) {
                @Override
                public Object getEntity() {
                    return menu;
                }
            });
        }
    }
}
