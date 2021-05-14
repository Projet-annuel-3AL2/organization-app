package com.agirpourtous.core.api.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class AddProjectRequest {
    private String name;

    public AddProjectRequest(String name) {
        this.name = name;
    }

    public AddProjectRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
