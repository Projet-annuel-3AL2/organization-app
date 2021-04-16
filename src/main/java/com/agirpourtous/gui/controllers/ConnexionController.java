package com.agirpourtous.gui.controllers;

import com.agirpourtous.Main;
import com.agirpourtous.core.api.APIClient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
        MainMenuController mainMenuController = new MainMenuController(client, stage);
        connectButton.setDisable(false);
    }

    @FXML
    public void onForgotPasswordClick() {
    }
}
