package com.agirpourtous.gui;

import com.agirpourtous.gui.controllers.ConnexionController;
import javafx.application.Application;
import javafx.stage.Stage;


public class GUILauncher extends Application {

    @Override
    public void start(Stage stage) {
        System.out.println("Start in GUI mode");
        new ConnexionController();
    }
}
