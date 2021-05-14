package com.agirpourtous.core.api.requests;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
public class AddCommentRequest {
    private String ticketId;
    private String userId;
    private String text;

    public AddCommentRequest(String ticketId, String userId, String text) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.text = text;
    }

    public AddCommentRequest() {
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
