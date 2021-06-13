package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Comment;

public class CommentMenu extends Menu{
    public CommentMenu(CLILauncher launcher, Comment comment) {
        super(launcher, "Menu de gestion d'un commentaire");
        addAction(new Action("Modifier le commentaire") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new MainMenu(launcher));
            }
        });
        addAction(new Action("Supprimer le commentaire") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new MainMenu(launcher));
            }
        });

        addAction(new Action("Retour au menu principal") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new MainMenu(launcher));
            }
        });
    }
}
