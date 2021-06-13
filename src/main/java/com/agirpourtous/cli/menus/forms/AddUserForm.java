package com.agirpourtous.cli.menus.forms;

import com.agirpourtous.core.api.requests.AddUserRequest;

public class AddUserForm extends Form {
    @Override
    public AddUserRequest askEntries() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setMail(emailField("Saisissez le mail:"));
        addUserRequest.setUsername(stringField("Saisissez le nom d'utilisateur:"));
        addUserRequest.setFirstname(stringField("Saisissez le prénom:"));
        addUserRequest.setLastname(stringField("Saisissez le nom:"));
        return addUserRequest;
    }
}
