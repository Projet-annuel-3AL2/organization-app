package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ConnexionController extends Controller {
    @FXML
    public Label usernameLabel;
    @FXML
    public TextField usernameField;
    @FXML
    public Label passwordLabel;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Hyperlink forgotPasswordLink;
    @FXML
    public CheckBox keepConnectionCheckBox;
    @FXML
    public Button connectButton;

    public ConnexionController(APIClient client) {
        super("connexion", client);
    }

    public ConnexionController(APIClient client, Stage stage) {
        super("connexion", client, stage);
    }

    @FXML
    public void onConnectClick() {
        /*connectButton.setDisable(true);
        Task<Boolean> task = new Task<>() {
            @Override
            protected Boolean call() {
                try {
                    client.getConnexion().connect(usernameField.getText(), passwordField.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        };
        task.stateProperty().addListener((observable, oldValue, newState) -> {
            if(newState==Worker.State.SUCCEEDED){
                MainMenuController mainMenuController = new MainMenuController(client);
                mainMenuController.showStage();
            }
        });
        new Thread(task).start();*/

        new MainMenuController(client, stage);
    }

    @FXML
    public void onForgotPasswordClick() {
    }

}
