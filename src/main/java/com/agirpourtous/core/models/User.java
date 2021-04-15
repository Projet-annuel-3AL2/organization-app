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

}
