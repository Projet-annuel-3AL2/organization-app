package com.agirpourtous.core.models;

import java.util.Date;

public class Comment {
    private Ticket ticket;
    private User user;
    private String id;
    private String text;
    private Date creationDate;

    public Ticket getTicket() {
        return ticket;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getText() {
        return text;
    }
}
