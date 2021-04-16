package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class MainMenuController extends Controller {

    public Label usernameLabel;
    public Button createUserButton;
    public Button createProjectButton;
    public Button disconnectButton;
    public Label projectLabel;
    public ScrollPane projectsHBox;

    public MainMenuController(APIClient client, Stage stage) {
        super("main_menu", client, stage);
    }
}
