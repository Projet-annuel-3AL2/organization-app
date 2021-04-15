package com.agirpourtous.models;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
    private Project project;
    private User creator;
    private User assignee;
    private ArrayList<Comment> comments;
    private String id;
    private String title;
    private String description;
    private TicketStatus status;
    private Date creationDate;
    private Date endDate;
    private float estimatedDuration;

}
