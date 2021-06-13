package com.agirpourtous.core.api.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ForgotPasswordRequest implements Request {
    private String username;

    public ForgotPasswordRequest(String username) {
        this.username = username;
    }

    public ForgotPasswordRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
