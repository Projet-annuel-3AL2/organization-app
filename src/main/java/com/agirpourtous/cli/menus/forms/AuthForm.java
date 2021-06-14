package com.agirpourtous.cli.menus.forms;

import com.agirpourtous.core.api.requests.LoginRequest;

public class AuthForm extends Form {
    public AuthForm() {
        super();
    }

    @Override
    public LoginRequest askEntries() {
        LoginRequest request = new LoginRequest();
        request.setUsername(this.stringField("Entrez votre nom d'utilisateur:"));
        request.setPassword(this.passwordField("Entrez votre mot de passe:"));
        return request;
    }
}
