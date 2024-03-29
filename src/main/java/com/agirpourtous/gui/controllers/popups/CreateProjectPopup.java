package com.agirpourtous.gui.controllers.popups;

import com.agirpourtous.core.api.requests.AddProjectRequest;
import com.agirpourtous.gui.controllers.Controller;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateProjectPopup extends Popup {
    public TextField nameField;
    public Label errorLabel;

    public CreateProjectPopup(Controller controller) throws IOException {
        super("Création de projet", "create_project_popup", controller);
    }

    public void onCreateProjectButton() {
        controller.getClient()
                .getProjectService()
                .create(new AddProjectRequest(nameField.getText()))
                .doOnError(clientResponse -> Platform.runLater(() -> errorLabel.setVisible(true)))
                .doOnSuccess(clientResponse -> Platform.runLater(this::close))
                .block();
    }
}
