package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.requests.ResetPasswordRequest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PasswordRecoveryController extends Controller {
    @FXML
    public Label errorField;
    @FXML
    public TextField tokenField;
    @FXML
    public TextField passwordField;
    @FXML
    public TextField confirmPasswordField;

    public PasswordRecoveryController(Controller controller) {
        super("password_recovery_menu", controller);
    }

    @FXML
    public void send() {
        if (passwordField.getText().equals(confirmPasswordField.getText()) && passwordField.getText().length() >= 7) {
            client.getAuthService()
                    .resetPassword(new ResetPasswordRequest(tokenField.getText(), passwordField.getText()))
                    .doOnSuccess(res -> Platform.runLater(() -> new ConnexionController(this)))
                    .subscribe();
        }
    }

    @FXML
    public void onBackToMainMenu() {
        new ConnexionController(this);
    }
}
