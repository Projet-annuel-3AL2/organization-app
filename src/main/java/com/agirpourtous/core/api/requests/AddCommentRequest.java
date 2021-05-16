package com.agirpourtous.core.api.requests;

public class AddCommentRequest {
    private String text;

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
