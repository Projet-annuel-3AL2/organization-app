package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.forms.AuthForm;
import com.agirpourtous.cli.menus.forms.ForgotPasswordForm;
import com.agirpourtous.cli.menus.forms.ResetPasswordForm;

public class AuthMenu extends Menu {

    public AuthMenu(CLILauncher launcher) {
        super(launcher, "Menu de connexion");
        addAction(new Action("Authentification") {
            @Override
            public void execute() {
                try {
                    launcher.getClient().connect(new AuthForm().askEntries())
                            .doOnSuccess(res -> launcher.setActiveMenu(new MainMenu(launcher)))
                            .doOnError(res -> launcher.setActiveMenu(new AuthMenu(launcher)))
                            .block();
                } catch (Exception ignored) {
                }
            }
        });
        addAction(new Action("Mot de passe oublié ou nouveau compte") {
            @Override
            public void execute() {
                try {
                    launcher.getClient().getAuthService()
                            .forgotPassword(new ForgotPasswordForm().askEntries().getUsername())
                            .doOnSuccess(res -> System.out.println("Un mail contenant le code de récupération vous a été envoyé"))
                            .doOnTerminate(() -> launcher.setActiveMenu(new AuthMenu(launcher)))
                            .block();
                } catch (Exception ignored) {
                }
            }
        });
        addAction(new Action("Réinitialiser le mot de passe avec un code de récupération") {
            @Override
            public void execute() {
                try {
                    launcher.getClient().getAuthService()
                            .resetPassword(new ResetPasswordForm().askEntries())
                            .doOnTerminate(() -> launcher.setActiveMenu(new AuthMenu(launcher)))
                            .block();
                } catch (Exception ignored) {
                }
            }
        });
    }
}
