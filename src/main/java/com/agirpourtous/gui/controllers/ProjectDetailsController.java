package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.gui.controllers.elements.TicketDetailsController;
import com.agirpourtous.gui.controllers.elements.TicketElementController;
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
    private final HashMap<String, TicketElementController> tickets;

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
    private Pane draggingTicket;
    private TicketDetailsController ticketDetails;


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

    public void onAddTicketClick() {

    }

    public void onAddUserClick() {

    }

    public void addTicket(Ticket ticket) {
        if (!tickets.containsKey(ticket.getId())) {
            try {
                Pane ticketList = (Pane) ((ScrollPane) ((Pane) ticketsListsHBox.getChildren().get(ticket.getStatus().ordinal())).getChildren().get(1)).getContent();
                TicketElementController ticketElementController = new TicketElementController(this, ticketList, ticket);
                Pane ticketElement = ticketElementController.ticketElement;
                ticketElement.setOnDragDetected(e -> setOnDragDetected(e, ticketElement));
                ticketElement.setOnMouseDragged(e -> e.setDragDetect(true));
                ticketElement.setOnDragDone(e -> draggingTicket = null);
                tickets.put(ticket.getId(), ticketElementController);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            tickets.get(ticket.getId()).updateTicket(ticket);
        }
    }

    public void setOnDragDetected(MouseEvent event, Pane ticketElement) {
        Dragboard db = ticketElement.startDragAndDrop(TransferMode.MOVE);
        db.setDragView(ticketElement.snapshot(null, null));
        ClipboardContent content = new ClipboardContent();
        content.putString("Ticket");
        db.setContent(content);
        draggingTicket = ticketElement;
        event.consume();
    }

    @FXML
    public void onTicketDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasString()) {
            ((Pane) draggingTicket.getParent()).getChildren().remove(draggingTicket);
            ((Pane) event.getGestureTarget()).getChildren().add(draggingTicket);
            event.setDropCompleted(true);
        }
        event.consume();
    }

    @FXML
    public void onTicketDragOver(DragEvent event) {
        if (draggingTicket != null && draggingTicket.getParent() != event.getGestureTarget()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
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
