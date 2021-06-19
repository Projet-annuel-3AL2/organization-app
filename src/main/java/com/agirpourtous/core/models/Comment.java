package com.agirpourtous.core.models;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;


@JsonAutoDetect
public class Comment extends Entity {
    private String ticketId;
    private Ticket ticket;
    private String userId;
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

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    // TODO : A Supprimer
    public Comment(Ticket ticket, String userId, User user, String text, Date creationDate) {
        this.ticket = ticket;
        this.userId = userId;
        this.user = user;
        this.text = text;
        this.creationDate = creationDate;
    }
}
