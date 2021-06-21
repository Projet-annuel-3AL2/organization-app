package com.agirpourtous.core.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect
public class User extends Entity {
    private final ArrayList<Ticket> createdTickets;
    private final ArrayList<Ticket> assignedTickets;
    private final ArrayList<Comment> comments;
    private boolean admin;
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

    @Override
    public String toString() {
        return username;
    }
}
