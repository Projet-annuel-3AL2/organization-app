package com.agirpourtous.cli.controller;

import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.User;

public class Show {

    public Show() {
    }

    public  void showProject(Project project) {
        System.out.println("----------\n" +
                "----- Name of Project : " + project.getName() + "\n" +
                "----- Id of Project : " + project.getId() + "\n" +
                "------- Admin(s) of Project :");
        project.getAdmins().forEach(this::showUser);

        System.out.println("------- Members of Project :");
        project.getMembers().forEach(this::showUser);

        System.out.println("------- Ticket of Project :");
        project.getTickets().forEach(this::showTicket);

    }

    public  void showTicket(Ticket ticket) {
        if (ticket == null){
            System.out.println("------- There is no Tickets");
        }else {
            System.out.println("------ Title : " + ticket.getTitle() + "  id: " + ticket.getId() + "\n" +
                    "------- Description : " + ticket.getDescription() + "\n" +
                    "------- Status : " + ticket.getStatus() + "  Priority :" + ticket.getPriority() + "\n" +
                    "------- Estimated Duration : " + ticket.getEstimatedDuration() + "\n" +
                    "--------");
        }
    }

    public void showUser(User user) {
        if (user == null){
            System.out.println("------- There is no member");
        }else {
            System.out.println("------ name : " + user.getUsername() + "  id: " + user.getId() + "\n" +
                    "------- mail : " + user.getMail() + "\n" +
                    "--------");
        }
    }

    public void showComment(Comment comment) {
        if (comment == null){
            System.out.println("------- There is no comment");
        }else {
            System.out.println("------ Username : " + comment.getUser().getUsername() + "\n" +
                    "------- Title : " + comment.getText() + "\n " +
                    "------- creation date : " + comment.getCreationDate() + "\n" +
                    "--------");
        }
    }
}
