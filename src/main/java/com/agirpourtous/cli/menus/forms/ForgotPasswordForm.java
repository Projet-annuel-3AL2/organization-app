package com.agirpourtous.cli.menus.forms;

import com.agirpourtous.core.api.requests.ForgotPasswordRequest;

public class ForgotPasswordForm extends Form {
    @Override
    public ForgotPasswordRequest askEntries() {
        return new ForgotPasswordRequest(stringField("Veuillez entrer votre nom d'utilisateur:"));
    }
}
