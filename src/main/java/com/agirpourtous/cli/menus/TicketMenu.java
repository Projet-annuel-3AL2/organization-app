package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.list.ProjectMemberListMenu;
import com.agirpourtous.cli.menus.list.TicketStatusListMenu;
import com.agirpourtous.core.api.requests.AddTicketRequest;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.TicketStatus;
import com.agirpourtous.core.models.User;
import com.agirpourtous.core.pdf.TicketPdfGenerator;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class TicketMenu extends Menu {
    public TicketMenu(CLILauncher launcher, Project project, Ticket ticket) {
        super(launcher, "Menu du ticket " + ticket.getTitle());

        addAction(new Action("Changer le status du ticket") {
            @Override
            public void execute() {
                AddTicketRequest addTicketRequest = new AddTicketRequest();
                addTicketRequest.setStatus((TicketStatus) new TicketStatusListMenu(launcher).startList());
                launcher.getClient()
                        .getTicketService()
                        .update(ticket.getId(), addTicketRequest)
                        .subscribe();
            }
        });
        addAction(new Action("Gérer les commentaires") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new CommentManagementMenu(launcher, ticket));
            }
        });
        addAction(new Action("Assigner le ticket à un membre du projet") {
            @Override
            public void execute() {
                User user = (User) new ProjectMemberListMenu(launcher, project).startList();
                if (user == null) {
                    launcher.setActiveMenu(new ProjectMenu(launcher, project));
                    return;
                }
                launcher.getClient()
                        .getTicketService()
                        .setAssignee(ticket.getId(), user.getId())
                        .subscribe();
            }
        });
        addAction(new Action("Exporter sous format PDF") {
            @Override
            public void execute() {
                try {
                    new TicketPdfGenerator(launcher.getClient(), ticket).generatePdf();
                } catch (DocumentException | IOException e) {
                    System.out.println("Erreur lors de l'écriture du fichier PDF");
                }
            }
        });
        addAction(new Action("Supprimer le ticket") {
            @Override
            public void execute() {
                launcher.getClient()
                        .getTicketService()
                        .delete(ticket.getId())
                        .subscribe();
                launcher.setActiveMenu(new ProjectMenu(launcher, project));
            }
        });
        addAction(new Action("Retour au menu du projet " + project.getName()) {
            @Override
            public void execute() {
                launcher.setActiveMenu(new ProjectMenu(launcher, project));
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
