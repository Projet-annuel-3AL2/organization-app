package com.agirpourtous.core.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect
public class Project extends Entity  {
    private final ArrayList<User> admins;
    private final ArrayList<Ticket> tickets;
    private final ArrayList<User> members;
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


    public String getName() {
        return name;
    }
}
