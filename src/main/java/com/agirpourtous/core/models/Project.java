package com.agirpourtous.core.models;

import java.util.ArrayList;

public class Project {
    private ArrayList<User> admins;
    private ArrayList<Ticket> tickets;
    private ArrayList<User> members;
    private String id;
    private String name;

    public ArrayList<User> getAdmins() {
        return admins;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
