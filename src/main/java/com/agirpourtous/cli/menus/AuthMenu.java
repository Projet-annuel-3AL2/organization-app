package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.menus.forms.AuthForm;
import com.agirpourtous.cli.menus.forms.ForgotPasswordForm;
import com.agirpourtous.cli.menus.forms.ResetPasswordForm;
import com.agirpourtous.core.api.APIClient;

public class AuthMenu extends Menu {

    public AuthMenu() {
        super("Menu de connexion");
        APIClient client = new APIClient();
        addAction(new Action("Authentification") {
            @Override
            public void execute() {
                client.connect(new AuthForm().askEntries())
                        .onErrorContinue((res, r) -> res.printStackTrace())
                        .doOnSuccess(res -> new MainMenu(client))
                        .doOnError(res -> new AuthMenu())
                        .block();
            }
        });
        addAction(new Action("Mot de passe oublié ou nouveau compte") {
            @Override
            public void execute() {
                client.getAuthService()
                        .forgotPassword(new ForgotPasswordForm().askEntries().getUsername())
                        .doOnSuccess(res -> System.out.println("Un mail contenant le code de récupération vous a été envoyé"))
                        .doOnTerminate(AuthMenu::new)
                        .block();
            }
        });
        addAction(new Action("Réinitialiser le mot de passe avec un code de récupération") {
            @Override
            public void execute() {
                client.getAuthService()
                        .resetPassword(new ResetPasswordForm().askEntries())
                        .doOnTerminate(AuthMenu::new)
                        .block();
            }
        });

        start();
    }
}
