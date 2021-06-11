package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.menus.forms.AuthForm;
import com.agirpourtous.core.api.APIClient;

public class AuthMenu extends Menu {

    public AuthMenu() {
        super("Menu de connexion");
        APIClient client = new APIClient();
        addAction(new Action("Authentification") {
            @Override
            public void execute() {
                client.getAuthService()
                        .login(new AuthForm().askEntries())
                        .doOnSuccess(res -> new HomePageMenu(client))
                        .doOnError(res -> new AuthMenu())
                        .subscribe();
            }
        });

        start();
    }
}
