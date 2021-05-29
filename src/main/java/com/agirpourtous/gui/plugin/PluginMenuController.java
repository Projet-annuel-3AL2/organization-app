package com.agirpourtous.gui.plugin;

import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.popups.Popup;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PluginMenuController extends Popup {
    @FXML
    VBox pluginList;

    public PluginMenuController(String title, String fxml, Controller controller) throws IOException {
        super("Plugin menu", "plugin_menu_popup", controller);
    }

}
