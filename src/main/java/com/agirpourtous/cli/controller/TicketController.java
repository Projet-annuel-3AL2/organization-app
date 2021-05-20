package com.agirpourtous.cli.controller;

import com.agirpourtous.cli.menus.CommentMenu;
import com.agirpourtous.cli.menus.TicketMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.AddCommentRequest;
import com.agirpourtous.core.api.requests.AddTicketRequest;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.TicketStatus;

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

        Ticket ticket = client.getTicketService().findById(idticket).block();
        if (ticket != null) {
            if (isAdminProject(client, ticket) || ticket.getCreatorId().equals(client.getUser().getId()))
                try {
                    client.getTicketService().delete(idticket);
                } catch (Exception e) {
                    System.out.println("There is no Ticket with the given id");
                }
        } else {
            System.out.println("There is no ticket with this id");
        }

        new TicketMenu(client);
    }

    public void updateTicket(APIClient client) {
        String assigneId = null;
        String title = null;
        String description = null;
        String ticketStatus = null;
        String idTicket = null;
        TicketStatus status = null;
        float estimatedDuration = -1;
        int priority = -1;

        System.out.println("Insert id of the Ticket you want to update : ");

        while (idTicket == null) {
            idTicket = SCANNER.next();
        }
        try {

            Ticket ticket = client.getTicketService().findById(idTicket).block();
            if (ticket != null) {
                if (isAdminProject(client, ticket) || ticket.getCreatorId().equals(client.getUser().getId())) {

                    System.out.println("Insert assigne id (enter to keep " + ticket.getAssigneeId() + " ) : ");
                    assigneId = SCANNER.next();
                    if (assigneId == null) {
                        assigneId = ticket.getAssigneeId();
                    }

                    System.out.println("Insert title (enter to keep " + ticket.getAssigneeId() + " ) : ");
                    title = SCANNER.next();
                    if (title == null) {
                        title = ticket.getTitle();
                    }

                    System.out.println("Insert description (enter to keep " + ticket.getDescription() + " ) : ");
                    description = SCANNER.next();
                    if (description == null) {
                        description = ticket.getDescription();
                    }

                    System.out.println("Insert Status <TODO>/<OPEN>/<CLOSED> (enter to keep " + ticket.getStatus() + " ) : ");
                    ticketStatus = SCANNER.next();
                    if (ticketStatus == null || (!ticketStatus.equals("TODO") && !ticketStatus.equals("OPEN") && !ticketStatus.equals("CLOSED"))) {
                        ticketStatus = ticket.getDescription();
                    }

                    System.out.println("Insert Estimated Duration (enter to keep " + ticket.getEstimatedDuration() + " ) : ");
                    estimatedDuration = SCANNER.nextFloat();
                    if (estimatedDuration < 0) {
                        estimatedDuration = ticket.getEstimatedDuration();
                    }

                    System.out.println("Insert Priority (enter to keep " + ticket.getPriority() + " ) : ");
                    priority = SCANNER.nextInt();
                    if (priority < 0) {
                        priority = ticket.getPriority();
                    }

                    System.out.println("Insert Status (enter to keep " + ticket.getStatus() + " ) : ");
                    String statusTemp = SCANNER.next();
                    if (statusTemp == null) {
                        status = ticket.getStatus();
                    } else if (statusTemp.equals("TODO")) {
                        status = TicketStatus.TODO;
                    } else if (statusTemp.equals("OPEN")) {
                        status = TicketStatus.OPEN;
                    } else if (statusTemp.equals("CLOSED")) {
                        status = TicketStatus.CLOSED;
                    }
                    try {

                        AddTicketRequest addTicketRequest = new AddTicketRequest(assigneId, title, description, estimatedDuration, priority, status);
                        try {
                            client.getTicketService().update(idTicket, addTicketRequest).block();

                        } catch (Exception e) {
                            System.out.println("An error occur while update of the ticket");
                        }
                    } catch (Exception e) {
                        System.out.println("An error while getting creator id");
                    }
                } else {
                    System.out.println("You are not admin of the project or you aren't the creator");
                }

            }
        } catch (Exception e) {
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

        Ticket ticket = client.getTicketService().findById(idticket).block();
        if (ticket != null) {

            if (isAdminProject(client, ticket)) {

                System.out.println("Insert id of the User you want to assign to ticket : ");

                String idUser = null;
                while (idUser == null) {
                    idUser = SCANNER.next();
                }

                try {
                    client.getTicketService().setAssignee(idticket, idUser);
                } catch (Exception e) {
                    System.out.println("An Error Occur with the set of a new Assigne");
                }
            } else {
                System.out.println("You are not admin of this project");
            }
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
        } catch (Exception e) {
            System.out.println("There is no Ticket with the given id");
        }


        new TicketMenu(client);
    }

    public void addCommentToTicket(APIClient client) {
        String idTicket = null;
        String text = null;

        System.out.println("Insert id of the ticket you want to add a comment : ");

        while (idTicket == null) {
            idTicket = SCANNER.next();
        }

        Ticket ticket = client.getTicketService().findById(idTicket).block();
        if (ticket != null) {
            if (isMemberProject(client, ticket)) {

                System.out.println("Insert text  for your comment : ");

                while (text == null) {
                    text = SCANNER.next();
                }

                AddCommentRequest addCommentRequest = new AddCommentRequest(text);
                try {
                    client.getTicketService().addComment(idTicket, addCommentRequest).block();
                } catch (Exception e) {
                    System.out.println("An Error occur while creating a new comment");
                }
            } else {
                System.out.println("You can't add a comment to this ticket, you are not a member of the project");
            }
        } else {
            System.out.println("There is no Ticket with this id");
        }

        new CommentMenu(client);
    }

    private boolean isAdminProject(APIClient client, Ticket ticket) {
        final boolean[] isGranted = new boolean[1];
        client.getProjectService().getAdmins(ticket.getProjectId()).subscribe(user -> {
            if (user.getId().equals(client.getUser().getId())) {
                isGranted[0] = true;
            }
        });
        return isGranted[0];
    }

    private boolean isMemberProject(APIClient client, Ticket ticket) {
        final boolean[] isGranted = new boolean[1];
        client.getProjectService().getMembers(ticket.getProjectId()).subscribe(user -> {
            if (user.getId().equals(client.getUser().getId())) {
                isGranted[0] = true;
            }
        });
        return isGranted[0];
    }
}