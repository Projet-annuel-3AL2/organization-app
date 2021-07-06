package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.forms.AddCommentForm;
import com.agirpourtous.core.api.requests.AddCommentRequest;
import com.agirpourtous.core.models.Comment;

public class CommentMenu extends Menu {
    public CommentMenu(CLILauncher launcher, Comment comment) {
        super(launcher, "Menu de gestion d'un commentaire");
        addAction(new Action("Modifier le commentaire") {
            @Override
            public void execute() {
                AddCommentRequest addCommentRequest = new AddCommentForm().askEntries();
                launcher.getClient()
                        .getCommentService()
                        .update(comment.getId(), addCommentRequest)
                        .subscribe();
                launcher.setActiveMenu(new CommentMenu(launcher, comment));
            }
        });
        addAction(new Action("Supprimer le commentaire") {
            @Override
            public void execute() {
                launcher.getClient()
                        .getCommentService()
                        .delete(comment.getId())
                        .subscribe();
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
