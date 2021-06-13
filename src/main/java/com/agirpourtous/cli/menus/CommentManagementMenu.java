package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.forms.AddCommentForm;
import com.agirpourtous.cli.menus.list.TicketCommentsListMenu;
import com.agirpourtous.core.api.requests.AddCommentRequest;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;

public class CommentManagementMenu extends Menu {
    public CommentManagementMenu(CLILauncher launcher, Ticket ticket) {
        super(launcher, "Menu de gestion des commentaires du ticket " + ticket.getTitle());
        addAction(new Action("Afficher les commentaires") {
            @Override
            public void execute() {
                Comment comment = (Comment) new TicketCommentsListMenu(launcher, ticket).startList();
                if (comment == null) {
                    launcher.setActiveMenu(new MainMenu(launcher));
                    return;
                }
                launcher.setActiveMenu(new CommentMenu(launcher, comment));
            }
        });
        addAction(new Action("Ajouter un commentaire") {
            @Override
            public void execute() {
                AddCommentRequest addCommentRequest = new AddCommentForm().askEntries();
                launcher.getClient()
                        .getTicketService()
                        .addComment(ticket.getId(), addCommentRequest)
                        .doOnSuccess(comment -> launcher.setActiveMenu(new CommentMenu(launcher, comment)))
                        .subscribe();
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
