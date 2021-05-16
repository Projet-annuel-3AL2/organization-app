package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.UpdateTicketRequest;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.TicketStatus;
import com.agirpourtous.gui.controllers.elements.TicketDetailsElement;
import com.agirpourtous.gui.controllers.elements.TicketElement;
import com.agirpourtous.gui.controllers.popups.CreateTicketPopup;
import com.agirpourtous.gui.controllers.popups.ProjectUsersManagementPopup;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import reactor.retry.Repeat;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectDetailsController extends Controller {
    private final HashMap<String, TicketElement> tickets;

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
    private Pane draggingTicketPane;
    private Ticket draggingTicket;
    private TicketDetailsElement ticketDetails;


    public ProjectDetailsController(APIClient client, Controller previous, Project project) {
        super("project_details", previous);
        this.tickets = new HashMap<>();
        this.project = project;
        project.getTickets().forEach(this::addTicket);
        client.getTicketService()
                .findAll()
                .collect(Collectors.toList())
                .repeatWhen(Repeat.onlyIf(repeatContext -> isActive)
                        .fixedBackoff(Duration.ofSeconds(10)))
                .subscribe(tickets -> Platform.runLater(() -> setTickets(tickets)));
    }

    private void setTickets(List<Ticket> ticketsList) {
        removeDeletedTickets(ticketsList);
        ticketsList.forEach(this::addTicket);
    }

    private void removeDeletedTickets(List<Ticket> ticketsList) {
        List<String> receivedIds = ticketsList.stream().map(Ticket::getId).collect(Collectors.toList());
        List<String> elementsDeleted = new ArrayList<>(tickets.keySet());
        elementsDeleted.removeAll(receivedIds);
        elementsDeleted.forEach(this::removeTicket);
    }

    private void removeTicket(String id) {
        tickets.get(id).remove();
        tickets.remove(id);
    }

    public void onMainMenuButtonClick() {
        isActive = false;
        new MainMenuController(client, this);
    }

    public void onAddTicketClick() throws IOException {
        new CreateTicketPopup(this);
    }

    public void onAddUserClick() throws IOException {
        new ProjectUsersManagementPopup(this);
    }

    public void addTicket(Ticket ticket) {
        if (!tickets.containsKey(ticket.getId())) {
            try {
                Pane ticketList = (Pane) ((ScrollPane) ((Pane) ticketsListsHBox.getChildren().get(ticket.getStatus().ordinal())).getChildren().get(1)).getContent();
                TicketElement ticketElementController = new TicketElement(this, ticketList, ticket);
                Pane ticketElement = ticketElementController.ticketElement;
                ticketElement.setOnDragDetected(e -> setOnDragDetected(e, ticketElementController));
                ticketElement.setOnMouseDragged(e -> e.setDragDetect(true));
                ticketElement.setOnDragDone(e -> draggingTicketPane = null);
                tickets.put(ticket.getId(), ticketElementController);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            tickets.get(ticket.getId()).updateTicket(ticket);
        }
    }

    @FXML
    public void setOnDragDetected(MouseEvent event, TicketElement ticketController) {
        Dragboard db = ticketController.ticketElement.startDragAndDrop(TransferMode.MOVE);
        db.setDragView(ticketController.ticketElement.snapshot(null, null));
        ClipboardContent content = new ClipboardContent();
        content.putString("Ticket");
        db.setContent(content);
        draggingTicketPane = ticketController.ticketElement;
        draggingTicket = ticketController.getTicket();
        event.consume();
    }

    @FXML
    public void onTicketDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasString()) {
            ((Pane) draggingTicketPane.getParent()).getChildren().remove(draggingTicketPane);
            ((Pane) event.getGestureTarget()).getChildren().add(draggingTicketPane);
            TicketStatus newStatus = TicketStatus.values()[ticketsListsHBox.getChildren().indexOf(((Pane) event.getGestureTarget()).getParent().getParent().getParent().getParent())];
            updateTicket(draggingTicket.getId(), newStatus);
            event.setDropCompleted(true);
        }
        event.consume();
    }

    private void updateTicket(String ticketId, TicketStatus status) {
        client.getTicketService()
                .update(ticketId, new UpdateTicketRequest(status))
                .subscribe();
    }

    @FXML
    public void onTicketDragOver(DragEvent event) {
        if (draggingTicketPane != null && draggingTicketPane.getParent() != event.getGestureTarget()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    public void displayTicketDetails(Ticket ticket) {
        if (ticketDetails != null) {
            ticketDetails.close();
        }
        try {
            this.ticketDetails = new TicketDetailsElement(this, ticketsListsHBox, ticket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Project getProject() {
        return project;
    }
}
