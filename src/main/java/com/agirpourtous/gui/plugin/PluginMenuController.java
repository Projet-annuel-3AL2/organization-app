package com.agirpourtous.gui.plugin;

import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.popups.Popup;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;

import java.io.IOException;

public class PluginMenuController extends Popup {
    @FXML
    VBox pluginList;
    private PluginManager pluginManager;
    public PluginMenuController(Controller controller) throws IOException {
        super("Plugin menu", "plugin_menu_popup", controller);
    }
    @FXML
    public void initialize() throws IOException {
        pluginManager = new DefaultPluginManager();
        pluginManager.loadPlugins();
        for(GuiPluginController controller: pluginManager.getExtensions(GuiPluginController.class)){
            controller.display(this, pluginList);
        }
    }
}
