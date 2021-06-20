package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class TicketPdfGenerator extends PdfGenerator {
    private final Ticket ticket;

    public TicketPdfGenerator(APIClient client, Ticket ticket) {
        super(client);
        this.ticket = ticket;
    }

    @Override
    public void generateDocument(Document document) throws DocumentException {
        document.add(new Paragraph("Ticket: " + ticket.getTitle(), getTextFont()));
        addCreatorName(document);
        addAssigneeName(document);
        document.add(new Paragraph("Status: " + ticket.getStatus(), getTextFont()));
        document.add(new Paragraph("Description: ", getTextFont()));
        document.add(new Paragraph(ticket.getDescription(), getTextFont()));
        document.add(new Paragraph("Date de création: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(ticket.getCreationDate()), getTextFont()));
        document.add(new Paragraph("Date de mise à jour: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(ticket.getUpdateDate()), getTextFont()));
        if (ticket.getEndDate() != null)
            document.add(new Paragraph("Date de fin: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(ticket.getEndDate()), getTextFont()));
        document.add(new Paragraph("Durée estimé: " + ticket.getEstimatedDuration(), getTextFont()));
        document.add(new Paragraph("Priorité: " + ticket.getPriority(), getTextFont()));
        document.add(new Paragraph(" "));
    }

    private void addCreatorName(Document document) throws DocumentException {
        User creator = client.getUserService()
                .findById(ticket.getCreatorId())
                .block();
        if (creator == null) {
            document.add(new Paragraph("Créateur: erreur: introuvable", getTextFont()));
            return;
        }
        document.add(new Paragraph("Créateur: " + creator.getUsername(), getTextFont()));
    }

    private void addAssigneeName(Document document) throws DocumentException {
        User assignee = client.getUserService()
                .findById(ticket.getAssigneeId())
                .block();
        if (assignee == null) {
            document.add(new Paragraph("Assigné à: erreur: introuvable", getTextFont()));
            return;
        }
        document.add(new Paragraph("Assigné à: " + assignee.getUsername(), getTextFont()));
        addComments(document);
    }

    private void addComments(Document document) throws DocumentException {
        List<Comment> comments = client.getTicketService()
                .getComments(ticket.getId())
                .collect(Collectors.toList())
                .block();
        document.add(new Paragraph("Commentaires: ", getTextFont()));
        if (comments == null) {
            document.add(new Paragraph("Il n'y a pas de commentaires", getTextFont()));
            return;
        }
        for (Comment comment : comments) {
            new CommentPdfGenerator(client, comment).generateDocument(document);
        }
    }
}
