package com.agirpourtous.core.api.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ResetPasswordRequest implements Request {
    private String password;
    private String token;

    public ResetPasswordRequest(String password, String token) {
        this.password = password;
        this.token = token;
    }

    public ResetPasswordRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
