package com.agirpourtous.core.models;

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
    private int priority;

    public Project getProject() {
        return project;
    }

    public User getCreator() {
        return creator;
    }

    public User getAssignee() {
        return assignee;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public float getEstimatedDuration() {
        return estimatedDuration;
    }

    public int getPriority() {
        return priority;
    }
}
