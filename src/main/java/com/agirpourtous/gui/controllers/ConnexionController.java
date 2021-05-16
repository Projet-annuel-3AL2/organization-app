package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.LoginRequest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    public ConnexionController(APIClient client, Controller previous) {
        super("connexion", previous);
        client.logout();
    }

    @FXML
    public void onConnectClick() {
        connectButton.setDisable(true);
        LoginRequest loginRequest = new LoginRequest(usernameField.getText(), passwordField.getText());
        client.connect(loginRequest)
                .doOnSuccess(response -> Platform.runLater(() -> {
                    client.setStayConnected(keepConnectionCheckBox.isSelected());
                    isActive = false;
                    new MainMenuController(client, this);
                }))
                .doOnError(response -> Platform.runLater(() -> connectButton.setDisable(false)))
                .subscribe();
    }

    @FXML
    public void onForgotPasswordClick() {
    }

}
