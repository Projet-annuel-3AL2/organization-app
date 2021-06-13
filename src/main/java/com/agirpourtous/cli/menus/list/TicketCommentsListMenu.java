package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketCommentsListMenu extends ListSelectionMenu {
    private final Ticket ticket;

    public TicketCommentsListMenu(CLILauncher launcher, Ticket ticket) {
        super(launcher, "Choisissez un commentaire");
        this.ticket = ticket;
    }

    @Override
    protected void loadEntityList() {
        List<Comment> comments = getComments();
        for (Comment comment : comments) {
            addAction(new ListAction(comment.getText()) {
                @Override
                public Comment getEntity() {
                    return comment;
                }
            });
        }
    }

    private List<Comment> getComments() {
        return launcher.getClient()
                .getTicketService()
                .getComments(ticket.getId())
                .collect(Collectors.toList())
                .block();
    }
}
