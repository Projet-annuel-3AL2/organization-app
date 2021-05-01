package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.LoginRequest;
import com.agirpourtous.core.models.User;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
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

    public ConnexionController() {
        super("connexion", new APIClient());
    }

    public ConnexionController(APIClient client, Stage stage) {
        super("connexion", client, stage);
    }

    @FXML
    public void onConnectClick() {
        connectButton.setDisable(true);
        LoginRequest loginRequest = new LoginRequest(usernameField.getText(), passwordField.getText());
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                User user = client.connect(loginRequest).block();
                if (user != null) {
                    this.succeeded();
                }else{
                    this.failed();
                }
                return null;
            }
        };
        task.stateProperty().addListener((observable, oldValue, newState) -> {
            if(newState== Worker.State.SUCCEEDED){
                new MainMenuController(client, stage);
            }
            if(newState== Worker.State.FAILED){
                connectButton.setDisable(false);
            }
        });
        new Thread(task).start();
    }

    @FXML
    public void onForgotPasswordClick() {
    }

}
