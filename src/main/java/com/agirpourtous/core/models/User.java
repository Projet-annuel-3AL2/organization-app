package com.agirpourtous.core.models;

import java.util.ArrayList;

public class User {
    private ArrayList<Ticket> createdTickets;
    private ArrayList<Ticket> assignedTickets;
    private ArrayList<Comment> comments;
    private String id;
    private boolean admin;
    private String username;
    private String lastname;
    private String firstname;
    private String mail;

    public ArrayList<Ticket> getCreatedTickets() {
        return createdTickets;
    }

    public ArrayList<Ticket> getAssignedTickets() {
        return assignedTickets;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getUsername() {
        return username;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMail() {
        return mail;
    }
}
