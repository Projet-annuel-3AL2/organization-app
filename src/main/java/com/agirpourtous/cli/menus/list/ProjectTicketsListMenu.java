package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectTicketsListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectTicketsListMenu(CLILauncher launcher, Project project) {
        super(launcher, "Sélectionnez un ticket");
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        try {
            List<Ticket> tickets = getTickets();
            for (Ticket ticket : tickets) {
                addAction(new ListAction(ticket.getTitle()) {
                    @Override
                    public Entity getEntity() {
                        return ticket;
                    }
                });
            }
        } catch (Exception ignored) {
        }
    }

    private List<Ticket> getTickets() {
        return launcher.getClient().getProjectService()
                .getTickets(project.getId())
                .collect(Collectors.toList())
                .block();
    }
}
