package com.agirpourtous.cli.controller;

import com.agirpourtous.cli.menus.CommentMenu;
import com.agirpourtous.cli.menus.TicketMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.AddCommentRequest;
import com.agirpourtous.core.api.requests.AddTicketRequest;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;

import java.util.Scanner;

public class TicketController {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Show show = new Show();

    public void getAllTicket(APIClient client) {
        System.out.println("List of all Tickets : ");
        client.getTicketService().findAll().subscribe(show::showTicket);

        new TicketMenu(client);
    }

    public void getTicketById(APIClient client) {
        System.out.println("Insert id of the ticket you want to display : ");

        String idticket = null;
        while (idticket == null) {
            idticket = SCANNER.next();
        }

        Ticket ticket = client.getTicketService().findById(idticket).block();
        show.showTicket(ticket);

        new TicketMenu(client);
    }

    public void removeTicketWithId(APIClient client) {
        System.out.println("Insert id of the ticket you want to remove : ");

        String idticket = null;
        while (idticket == null) {
            idticket = SCANNER.next();
        }

        try {
            client.getTicketService().delete(idticket);
        }catch (Exception e){
            System.out.println("There is no Ticket with the given id");
        }

        new TicketMenu(client);
    }

    public void updateTicket(APIClient client) {
        String projectId = null;
        String creatorId = null;
        String assigneId = null;
        String title = null;
        String description = null;
        String ticketStatus = null;
        String idTicket = null;
        float estimatedDuration = -1;
        int priority = -1;

        System.out.println("Insert id of the Ticket you want to update : ");

        while (idTicket == null) {
            idTicket = SCANNER.next();
        }
        try {

            Ticket ticket =  client.getTicketService().findById(idTicket).block();
            if (ticket != null){

                System.out.println("Insert Project id (enter to keep " + ticket.getProjectId() +" ) : ");
                projectId = SCANNER.next();
                if (projectId == null){
                    projectId = ticket.getProjectId();
                }

                System.out.println("Insert creator id (enter to keep " + ticket.getCreatorId() +" ) : ");
                creatorId = SCANNER.next();
                if (creatorId == null){
                    creatorId = ticket.getCreatorId();
                }

                System.out.println("Insert assigne id (enter to keep " + ticket.getAssigneeId() +" ) : ");
                assigneId = SCANNER.next();
                if (assigneId == null){
                    assigneId = ticket.getAssigneeId();
                }

                System.out.println("Insert title (enter to keep " + ticket.getAssigneeId() +" ) : ");
                title = SCANNER.next();
                if (title == null){
                    title = ticket.getTitle();
                }

                System.out.println("Insert description (enter to keep " + ticket.getDescription() +" ) : ");
                description = SCANNER.next();
                if (description == null){
                    description = ticket.getDescription();
                }

                System.out.println("Insert Status <TODO>/<OPEN>/<CLOSED> (enter to keep " + ticket.getStatus() +" ) : ");
                ticketStatus = SCANNER.next();
                if (ticketStatus == null || (!ticketStatus.equals("TODO") && !ticketStatus.equals("OPEN") && !ticketStatus.equals("CLOSED"))){
                    ticketStatus = ticket.getDescription();
                }

                System.out.println("Insert Estimated Duration (enter to keep " + ticket.getEstimatedDuration() +" ) : ");
                estimatedDuration = SCANNER.nextFloat();
                if (estimatedDuration < 0){
                    estimatedDuration = ticket.getEstimatedDuration();
                }

                System.out.println("Insert Priority (enter to keep " + ticket.getPriority() +" ) : ");
                priority = SCANNER.nextInt();
                if (priority < 0){
                    priority = ticket.getPriority();
                }

                try {
                    creatorId = client.getUser().getId();

                    AddTicketRequest addTicketRequest = new AddTicketRequest(projectId,creatorId,assigneId,title,description,estimatedDuration,priority);
                    try {
                        client.getTicketService().update(idTicket, addTicketRequest).block();

                    }catch (Exception e){
                        System.out.println("An error occur while update of the ticket");
                    }
                }catch (Exception e){
                    System.out.println("An error while getting creator id");
                }

            }
        }catch (Exception e){
            System.out.println("There is no ticket with the given id");
        }

        new TicketMenu(client);
    }

    public void setAssigneeWithIdUserAndIdTicket(APIClient client) {
        System.out.println("Insert id of the ticket you want to set a new assigne : ");

        String idticket = null;
        while (idticket == null) {
            idticket = SCANNER.next();
        }

        System.out.println("Insert id of the User you want to assign to ticket : ");

        String idUser = null;
        while (idUser == null) {
            idUser = SCANNER.next();
        }

        try {
            client.getTicketService().setAssignee(idticket, idUser);
        }catch (Exception e){
            System.out.println("An Error Occur with the set of a new Assigne");
        }

        new TicketMenu(client);
    }

    public void getCommentOfTicket(APIClient client) {
        System.out.println("Insert id of the ticket : ");

        String idTicket = null;
        while (idTicket == null) {
            idTicket = SCANNER.next();
        }

        System.out.println("------- List of Comments for ticket id " + idTicket + " :");
        try {
            client.getTicketService().getComments(idTicket).subscribe(show::showComment);
        }catch (Exception e){
            System.out.println("There is no Ticket with the given id");
        }


        new TicketMenu(client);
    }

    public void addCommentToTicket(APIClient client) {
        String idTicket = null;
        String userId = client.getUser().getId();
        String text = null;
        String projectId = null;

        System.out.println("Insert id of the Project : ");

        while (projectId == null) {
            projectId = SCANNER.next();
        }

        System.out.println("Insert id of the ticket you want to add a comment : ");

        while (idTicket == null) {
            idTicket = SCANNER.next();
        }

        System.out.println("Insert text  for your comment : ");

        while (text == null) {
            text = SCANNER.next();
        }

        Comment newComment = new Comment();
        AddCommentRequest addCommentRequest = new AddCommentRequest(idTicket,userId,text);
        try {
            client.getTicketService().addComment(idTicket, addCommentRequest).block();
        }catch (Exception e){
            System.out.println("An Error occur while creating a new comment");
        }

        new CommentMenu(client);
    }
}
