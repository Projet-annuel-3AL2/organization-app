package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.gui.controllers.elements.TicketDetailsController;
import com.agirpourtous.gui.controllers.elements.TicketElementController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectDetailsController extends Controller {
    private final Project project;
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
    private TicketDetailsController ticketDetails;

    public ProjectDetailsController(APIClient client, Stage stage, Project project) {
        super("project_details", client, stage);
        this.project = project;
        project.getTickets().forEach(this::addTicket);
    }

    public void onMainMenuButtonClick() {
        new MainMenuController(client, stage);
    }

    public void onAddTicketClick() {
    }

    public void addTicket(Ticket ticket) {
        try {
            Pane ticketList = (Pane) ((ScrollPane) ((Pane) ticketsListsHBox.getChildren().get(ticket.getStatus().ordinal())).getChildren().get(1)).getContent();
            new TicketElementController(this, ticketList, ticket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayTicketDetails(Ticket ticket) {
        if (ticketDetails != null) {
            ticketDetails.close();
        }
        try {
            this.ticketDetails = new TicketDetailsController(this, ticketsListsHBox, ticket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
