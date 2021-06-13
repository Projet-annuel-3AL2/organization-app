package com.agirpourtous.cli.menus.forms;

import com.agirpourtous.core.api.requests.AddProjectRequest;

public class AddProjectForm extends Form {
    public AddProjectForm() {
    }

    @Override
    public AddProjectRequest askEntries() {
        AddProjectRequest addProjectRequest = new AddProjectRequest();
        addProjectRequest.setName(stringField("Choisissez un nom pour le projet:"));
        return addProjectRequest;
    }
}
