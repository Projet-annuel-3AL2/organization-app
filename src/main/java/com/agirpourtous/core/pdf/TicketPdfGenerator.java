package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import java.text.SimpleDateFormat;

public class TicketPdfGenerator extends PdfGenerator {
    private final Ticket ticket;

    public TicketPdfGenerator(APIClient client, Ticket ticket) throws DocumentException {
        super(client);
        this.ticket = ticket;
        getDocument().add(new Chunk(ticket.getTitle(), getTitleFont()));
    }

    @Override
    public Document generateDocument(Document document) throws DocumentException {
        document.add(new Paragraph("Titre: " + ticket.getTitle(), getTextFont()));
        new Paragraph("\tCréateur: " + ticket.getCreator().getUsername(), getTextFont());
        new Paragraph("\tAssignee: " + ticket.getAssignee().getUsername(), getTextFont());
        new Paragraph("\tStatus: " + ticket.getStatus(), getTextFont());
        new Paragraph("\tDescription: ", getTextFont());
        new Paragraph("\t\t" + ticket.getDescription(), getTextFont());
        new Paragraph("\tDate de création: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ticket.getCreationDate()), getTextFont());
        new Paragraph("\tDate de mise à jour: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ticket.getUpdateDate()), getTextFont());
        new Paragraph("\tDate de fin: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ticket.getEndDate()), getTextFont());
        new Paragraph("\tDurée estimé: " + ticket.getEstimatedDuration(), getTextFont());
        new Paragraph("\tPriorité: " + ticket.getPriority(), getTextFont());
        new Paragraph("\tCommentaires: ", getTextFont());
        for (Comment comment : ticket.getComments()) {
            new CommentPdfGenerator(client, comment).generateDocument(document);
        }
        return document;
    }
}
