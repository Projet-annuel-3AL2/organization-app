package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class MainMenuController extends Controller {
    @FXML
    public Label usernameLabel;
    @FXML
    public Button createUserButton;
    @FXML
    public Button createProjectButton;
    @FXML
    public Button disconnectButton;
    @FXML
    public Label projectLabel;
    @FXML
    public ScrollPane projectsHBox;

    public MainMenuController(APIClient client, Stage stage) {
        super("main_menu", client, stage);
        if (client.getConnexion().getUser() != null)
            this.usernameLabel.setText(client.getConnexion().getUser().getUsername());
    }

    @FXML
    public void onCreateUserClick() {

    }

    @FXML
    public void onProjectUserClick() {

    }

    @FXML
    public void onDisconnectClick() {
        new ConnexionController(client, stage);
    }
}
