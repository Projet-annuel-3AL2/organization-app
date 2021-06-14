package com.agirpourtous.core.api.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class AddUserRequest implements Request {
    private String username;
    private String mail;
    private String firstname;
    private String lastname;
    private boolean isAdmin;

    public AddUserRequest(String username, String mail, String firstname, String lastname, boolean isAdmin) {
        this.username = username;
        this.mail = mail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
    }

    public AddUserRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
