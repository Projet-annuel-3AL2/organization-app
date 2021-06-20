package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.User;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import java.text.SimpleDateFormat;

public class TicketPdfGenerator extends PdfGenerator {
    private final Ticket ticket;

    public TicketPdfGenerator(APIClient client, Ticket ticket) {
        super(client);
        this.ticket = ticket;
    }

    @Override
    public void generateDocument(Document document) throws DocumentException {
        document.add(new Paragraph("Titre: " + ticket.getTitle(), getTextFont()));
        addCreatorName(document);
        addAssigneeName(document);
        document.add(new Paragraph(Chunk.TAB + "Status: " + ticket.getStatus(), getTextFont()));
        document.add(new Paragraph(Chunk.TAB + "Description: ", getTextFont()));
        document.add(new Paragraph(Chunk.TAB + Chunk.TAB + ticket.getDescription(), getTextFont()));
        document.add(new Paragraph(Chunk.TAB + "Date de création: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ticket.getCreationDate()), getTextFont()));
        document.add(new Paragraph(Chunk.TAB + "Date de mise à jour: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ticket.getUpdateDate()), getTextFont()));
        if (ticket.getEndDate() != null)
            document.add(new Paragraph(Chunk.TAB + "Date de fin: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ticket.getEndDate()), getTextFont()));
        document.add(new Paragraph(Chunk.TAB + "Durée estimé: " + ticket.getEstimatedDuration(), getTextFont()));
        document.add(new Paragraph(Chunk.TAB + "Priorité: " + ticket.getPriority(), getTextFont()));
        document.add(new Paragraph(Chunk.TAB + "Commentaires: ", getTextFont()));
        for (Comment comment : ticket.getComments()) {
            new CommentPdfGenerator(client, comment).generateDocument(document);
        }
    }

    private void addCreatorName(Document document) throws DocumentException {
        User creator = client.getUserService()
                .findById(ticket.getCreatorId())
                .block();
        if (creator == null) {
            document.add(new Paragraph(Chunk.TAB + "Créateur: erreur: introuvable", getTextFont()));
            return;
        }
        document.add(new Paragraph(Chunk.TAB + "Créateur: " + creator.getUsername(), getTextFont()));
    }

    private void addAssigneeName(Document document) throws DocumentException {
        User assignee = client.getUserService()
                .findById(ticket.getAssigneeId())
                .block();
        if (assignee == null) {
            document.add(new Paragraph(Chunk.TAB + "Assigné à: erreur: introuvable", getTextFont()));
            return;
        }
        document.add(new Paragraph(Chunk.TAB + "Assigné à: " + assignee.getUsername(), getTextFont()));
    }
}
