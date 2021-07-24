package com.agirpourtous.gui.plugin;

import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.popups.Popup;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;

import java.io.IOException;
import java.nio.file.Path;

public class PluginMenuController extends Popup {
    @FXML
    VBox pluginList;

    public PluginMenuController(Controller controller) throws IOException {
        super("Plugin menu", "plugin_menu_popup", controller);
    }

    @FXML
    public void initialize() throws IOException {
        PluginManager pluginManager = new DefaultPluginManager(Path.of("./plugins"));
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
        for (GuiPluginController controller : pluginManager.getExtensions(GuiPluginController.class)) {
            controller.display(this.controller, pluginList);
        }
    }
}
