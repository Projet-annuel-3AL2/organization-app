package com.agirpourtous.core.api.requests;

import com.agirpourtous.core.models.TicketStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class AddTicketRequest implements Request {

    private String assigneeId;
    private String title;
    private String description;
    private TicketStatus status;
    private float estimatedDuration;
    private int priority;

    public AddTicketRequest(String assigneeId, String title, String description, float estimatedDuration, int priority, TicketStatus status) {
        this.assigneeId = assigneeId;
        this.title = title;
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.priority = priority;
        this.status = status;
    }

    public AddTicketRequest(String text, String text1) {
        this.title = text;
        this.description = text1;
    }

    public AddTicketRequest(TicketStatus status) {
        this.status = status;
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
