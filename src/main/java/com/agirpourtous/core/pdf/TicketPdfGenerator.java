package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Ticket;
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
        document.add(new Paragraph("\tCréateur: " + ticket.getCreator().getUsername(), getTextFont()));
        document.add(new Paragraph("\tAssignee: " + ticket.getAssignee().getUsername(), getTextFont()));
        document.add(new Paragraph("\tStatus: " + ticket.getStatus(), getTextFont()));
        document.add(new Paragraph("\tDescription: ", getTextFont()));
        document.add(new Paragraph("\t\t" + ticket.getDescription(), getTextFont()));
        document.add(new Paragraph("\tDate de création: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ticket.getCreationDate()), getTextFont()));
        document.add(new Paragraph("\tDate de mise à jour: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ticket.getUpdateDate()), getTextFont()));
        document.add(new Paragraph("\tDate de fin: " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ticket.getEndDate()), getTextFont()));
        document.add(new Paragraph("\tDurée estimé: " + ticket.getEstimatedDuration(), getTextFont()));
        document.add(new Paragraph("\tPriorité: " + ticket.getPriority(), getTextFont()));
        document.add(new Paragraph("\tCommentaires: ", getTextFont()));
        for (Comment comment : ticket.getComments()) {
            new CommentPdfGenerator(client, comment).generateDocument(document);
        }
    }
}
