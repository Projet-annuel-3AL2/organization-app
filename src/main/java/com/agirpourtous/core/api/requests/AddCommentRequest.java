package com.agirpourtous.core.api.requests;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
public class AddCommentRequest {
    private String text;

    public AddCommentRequest() {
    }

    public AddCommentRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
