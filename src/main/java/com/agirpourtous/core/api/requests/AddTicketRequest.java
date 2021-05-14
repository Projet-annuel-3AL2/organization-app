package com.agirpourtous.core.api.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class AddTicketRequest {
    private String projectId;
    private String creatorId;
    private String assigneeId;
    private String title;
    private String description;
    private float estimatedDuration;
    private int priority;

    public AddTicketRequest(String projectId, String creatorId, String assigneeId, String title, String description, float estimatedDuration, int priority) {
        this.projectId = projectId;
        this.creatorId = creatorId;
        this.assigneeId = assigneeId;
        this.title = title;
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.priority = priority;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(float estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
