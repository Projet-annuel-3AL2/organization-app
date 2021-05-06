package com.agirpourtous.core.models;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;


@JsonAutoDetect
public class Comment extends Entity {
    private Ticket ticket;
    private User user;
    private String text;
    private Date creationDate;

    public Ticket getTicket() {
        return ticket;
    }

    public User getUser() {
        return user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getText() {
        return text;
    }
}
