package com.agirpourtous.gui.controllers;

import com.agirpourtous.gui.GUILauncher;
import javafx.fxml.FXML;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        GUILauncher.setRoot("primary");
    }
}