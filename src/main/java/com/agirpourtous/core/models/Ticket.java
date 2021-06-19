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

    // TODO : A Supprimer
    public Ticket(ArrayList<Comment> comments, String projectId, Project project, String creatorId, User creator, String assigneeId, User assignee, String title, String description, TicketStatus status, Date creationDate, Date updateDate, Date endDate, float estimatedDuration, int priority) {
        this.comments = comments;
        this.projectId = projectId;
        this.project = project;
        this.creatorId = creatorId;
        this.creator = creator;
        this.assigneeId = assigneeId;
        this.assignee = assignee;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.endDate = endDate;
        this.estimatedDuration = estimatedDuration;
        this.priority = priority;
    }
}
