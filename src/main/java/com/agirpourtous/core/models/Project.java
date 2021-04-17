package com.agirpourtous.core.models;

import java.util.ArrayList;

public class Project {
    private final ArrayList<User> admins;
    private final ArrayList<Ticket> tickets;
    private final ArrayList<User> members;
    private String id;
    private String name;

    public Project() {
        super();
        this.admins = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.members = new ArrayList<>();
    }

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
