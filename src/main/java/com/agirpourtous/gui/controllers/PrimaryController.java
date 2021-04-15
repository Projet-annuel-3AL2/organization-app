package com.agirpourtous.gui.controllers;

import com.agirpourtous.gui.GUILauncher;
import javafx.fxml.FXML;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        GUILauncher.setRoot("secondary");
    }
}
