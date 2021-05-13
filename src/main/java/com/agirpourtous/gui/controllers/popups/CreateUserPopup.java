package com.agirpourtous.gui.controllers.popups;

import com.agirpourtous.core.api.requests.AddUserRequest;
import com.agirpourtous.gui.controllers.Controller;
import javafx.application.Platform;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateUserPopup extends Popup {
    public TextField usernameField;
    public TextField mailField;
    public TextField firstnameField;
    public TextField lastnameField;
    public CheckBox isAdminCheckbox;
    public Label errorLabel;

    public CreateUserPopup(Controller controller) throws IOException {
        super("Ajout d'utilisateur", "add_user_popup", controller);
    }

    public void onCreateUserButton() {
        AddUserRequest addUserRequest = new AddUserRequest(usernameField.getText(), mailField.getText(), firstnameField.getText(), lastnameField.getText(), isAdminCheckbox.isSelected());
        controller.getClient()
                .getUserService()
                .create(addUserRequest)
                .doOnError(clientResponse -> Platform.runLater(() -> errorLabel.setVisible(true)))
                .doOnSuccess(clientResponse -> Platform.runLater(this::close))
                .block();
    }
}
