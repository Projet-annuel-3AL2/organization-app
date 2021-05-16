package com.agirpourtous.gui.controllers.popups;

        import com.agirpourtous.core.api.requests.AddTicketRequest;
        import com.agirpourtous.gui.controllers.Controller;
        import com.agirpourtous.gui.controllers.ProjectDetailsController;
        import javafx.application.Platform;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;

        import java.io.IOException;

public class CreateTicketPopup extends Popup {
    public TextField nameField;
    public TextArea descriptionField;

    public CreateTicketPopup(Controller controller) throws IOException {
        super("Création de ticket", "create_ticket_popup", controller);
    }

    public void onCreateClick() {
        controller.getClient()
                .getProjectService()
                .addTicket(((ProjectDetailsController) controller)
                                .getProject()
                                .getId(),
                        new AddTicketRequest(nameField.getText(), descriptionField.getText()))
                .doOnSuccess(response -> Platform.runLater(this::close))
                .subscribe();
    }
}
