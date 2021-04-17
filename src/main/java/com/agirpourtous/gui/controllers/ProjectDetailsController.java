package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectDetailsController extends Controller {
    @FXML
    public Button mainMenuButton;
    @FXML
    public Button addTicketButton;
    @FXML
    public Button addUserButton;
    @FXML
    public HBox ticketsListsHBox;
    @FXML
    public VBox todoTicketsVBox;
    @FXML
    public VBox openTicketsVBox;
    @FXML
    public VBox closedTicketsVBox;
    private final Project project;

    public ProjectDetailsController(APIClient client, Stage stage, Project project) {
        super("project_details", client, stage);
        this.project = project;
    }

    public void onMainMenuButtonClick(ActionEvent actionEvent) {
    }

    public void onAddTicketClick(ActionEvent actionEvent) {
    }
}
