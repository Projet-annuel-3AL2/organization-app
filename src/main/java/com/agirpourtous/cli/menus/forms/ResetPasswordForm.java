package com.agirpourtous.cli.menus.forms;

import com.agirpourtous.core.api.requests.ResetPasswordRequest;

public class ResetPasswordForm extends Form {

    @Override
    public ResetPasswordRequest askEntries() {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setToken(stringField("Veuillez saisir le code de validation reçu par mail:"));
        resetPasswordRequest.setPassword(passwordField("Veuillez saisir le nouveau mot de passe:"));
        return resetPasswordRequest;
    }
}
