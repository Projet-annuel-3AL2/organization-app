package com.agirpourtous.core.api.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class UsersManagementRequest {
    private List<String> userIds;

    public UsersManagementRequest(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getUsers() {
        return userIds;
    }

    public void setUsers(List<String> users) {
        this.userIds = users;
    }
}
