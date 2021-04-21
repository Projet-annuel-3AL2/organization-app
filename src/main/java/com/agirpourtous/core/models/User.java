package com.agirpourtous.core.models;

import java.util.ArrayList;

public class User {
    private final ArrayList<Ticket> createdTickets;
    private final ArrayList<Ticket> assignedTickets;
    private final ArrayList<Comment> comments;
    private String id;
    private boolean isAdmin;
    private String username;
    private String lastname;
    private String firstname;
    private String mail;

    public User() {
        super();
        this.comments = new ArrayList<>();
        this.createdTickets = new ArrayList<>();
        this.assignedTickets = new ArrayList<>();
    }

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
        return isAdmin;
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
