package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectPdfGenerator extends PdfGenerator {
    private final Project project;

    public ProjectPdfGenerator(APIClient client, Project project) throws DocumentException {
        super(client);
        this.project = project;
    }

    @Override
    public void generateDocument(Document document) throws DocumentException {
        document.add(new Paragraph("Nom du Projet: " + project.getName(), getTextFont()));
        addAdmins(document);
        addMembers(document);
        addTickets(document);
    }

    private void addAdmins(Document document) throws DocumentException {
        List<User> admins = client.getProjectService()
                .getAdmins(project.getId())
                .collect(Collectors.toList())
                .block();
        if (admins == null)
            return;
        document.add(new Paragraph("Liste des admins:", getTitleFont()));
        for (User user : admins) {
            new UserPdfGenerator(client, user).generateDocument(document);
        }
        document.add(new Paragraph(" "));
    }

    private void addMembers(Document document) throws DocumentException {
        List<User> members = client.getProjectService()
                .getMembers(project.getId())
                .collect(Collectors.toList())
                .block();
        if (members == null)
            return;
        document.add(new Paragraph("Liste des utilisateurs:", getTitleFont()));
        for (User user : members) {
            new UserPdfGenerator(client, user).generateDocument(document);
        }
    }

    private void addTickets(Document document) throws DocumentException {
        List<Ticket> tickets = client.getProjectService()
                .getTickets(project.getId())
                .collect(Collectors.toList())
                .block();
        document.add(new Paragraph("Liste des tickets:", getTitleFont()));
        if (tickets == null) {
            document.add(new Paragraph("Il n'y a pas de tickets pour le projet " + project.getName(), getTextFont()));
            return;
        }
        for (Ticket ticket : tickets) {
            new TicketPdfGenerator(client, ticket).generateDocument(document);
        }
    }
}
