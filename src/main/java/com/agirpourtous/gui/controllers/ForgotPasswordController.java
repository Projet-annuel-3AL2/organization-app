package com.agirpourtous.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ForgotPasswordController extends Controller {
    @FXML
    public Label errorLabel;
    @FXML
    public TextField usernameTextField;

    public ForgotPasswordController(Controller previousController) {
        super("forgot_password_menu", previousController);
    }

    @FXML
    public void initialize() {
        errorLabel.managedProperty().bind(errorLabel.visibleProperty());
    }

    public void onSendRecoveryMail() {
        client.getAuthService()
                .forgotPassword(usernameTextField.getText())
                .doOnSuccess(res -> Platform.runLater(() -> new PasswordRecoveryController(this)))
                .subscribe();
    }

    public void onBackToMenu() {
        new ConnexionController(this);
    }
}
