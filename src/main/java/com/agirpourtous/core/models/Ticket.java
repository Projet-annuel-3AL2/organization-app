package com.agirpourtous.core.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.Date;

@JsonAutoDetect
public class Ticket extends Entity {
    private final ArrayList<Comment> comments;
    private String projectId;
    private Project project;
    private String creatorId;
    private User creator;
    private String assigneeId;
    private User assignee;
    private String title;
    private String description;
    private TicketStatus status;
    private Date creationDate;
    private Date updateDate;
    private Date endDate;
    private float estimatedDuration;
    private int priority;

    public Ticket() {
        super();
        this.comments = new ArrayList<>();
    }

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

    public String getProjectId() {
        return projectId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
