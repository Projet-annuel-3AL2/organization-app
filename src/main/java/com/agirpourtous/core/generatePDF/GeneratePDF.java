package com.agirpourtous.core.generatePDF;

import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.stream.Stream;

public class GeneratePDF {

    public static void generatePDF(Project project) {
        System.out.println(project);

        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/pdf/"+project.getName() + "_" + UUID.randomUUID()));
            Font fontTitre = FontFactory.getFont(FontFactory.COURIER, 24, BaseColor.BLACK);
            Font fontText = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            document.open();

            Chunk chunkTitre = new Chunk("Nom du Projet : " + project.getName(), fontTitre);

            Chunk chunkAdmin = new Chunk("List des membres : ", fontText);
            PdfPTable tableAdmin = new PdfPTable(4);
            addTableUserHeader(tableAdmin);
            project.getAdmins().forEach(user -> addRowsMember(tableAdmin, user));

            Chunk chunkMembres = new Chunk("List des membres : ", fontText);
            PdfPTable tableMembers = new PdfPTable(4);

            addTableUserHeader(tableMembers);
            project.getMembers().forEach(user -> addRowsMember(tableMembers, user));

            document.add(chunkTitre);
            document.add(chunkAdmin);
            document.add(tableAdmin);
            document.add(chunkMembres);
            document.add(tableMembers);

            Chunk chunkListTicket = new Chunk("Liste des tickets : \n");
            document.add(chunkListTicket);

            project.getTickets().forEach(ticket -> addTicket(document, ticket));

            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void addTicket(Document document, Ticket ticket) {
        Font fontText = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);

        Chunk vide = new Chunk("\n");
        Chunk title = new Chunk( "Titre : " +ticket.getTitle(), fontText);
        Chunk creator = new Chunk("Créateur : " +ticket.getCreator().getUsername(), fontText);
        Chunk assignee = new Chunk("Assignee : " +ticket.getAssignee().getUsername(), fontText);
        Chunk status = new Chunk("Status : " +ticket.getStatus(), fontText);
        Chunk description = new Chunk("Description : " + ticket.getDescription(), fontText);
        Chunk creationDate = new Chunk("Date de création : " + ticket.getCreationDate(), fontText);
        Chunk updateDate = new Chunk("Date de mise à jour : : " + ticket.getCreationDate(), fontText);
        Chunk endDate = new Chunk("Date de fin : " + ticket.getCreationDate(), fontText);
        Chunk estimatedDuration = new Chunk("Durée estimé : " + ticket.getEstimatedDuration(), fontText);
        Chunk priority = new Chunk("Priorité : " + ticket.getPriority(), fontText);

        Chunk listComment = new Chunk("List des Commentaires : ", fontText);
        PdfPTable tableComment = new PdfPTable(3);
        addTableCommentHeader(tableComment);
        ticket.getComments().forEach(comment -> addRowsComment(tableComment, comment));

        try {
            document.add(vide);
            document.add(title);
            document.add(creator);
            document.add(assignee);
            document.add(status);
            document.add(creationDate);
            document.add(updateDate);
            document.add(endDate);
            document.add(estimatedDuration);
            document.add(priority);
            document.add(description);
            document.add(listComment);
            document.add(tableComment);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private static void addRowsComment(PdfPTable tableComment, Comment comment) {
        addCell(tableComment, comment.getUser().getUsername());
        addCell(tableComment, comment.getCreationDate().toString());
        addCell(tableComment, comment.getText());
    }

    private static void addTableCommentHeader(PdfPTable tableComment) {
        Stream.of("Username", "Message", "Date de Création")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    tableComment.addCell(header);
                });
    }

    private static void addCell(PdfPTable table, String data) {
        PdfPCell cell = new PdfPCell(new Phrase(data));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private static void addRowsMember(PdfPTable tableMembers, User user) {
        addCell(tableMembers, user.getUsername());
        addCell(tableMembers, user.getFirstname());
        addCell(tableMembers, user.getLastname());
        addCell(tableMembers, user.getMail());
    }

    private static void addTableUserHeader(PdfPTable tableMembers) {
        Stream.of("Username", "Lastname", "Firstname", "Mail")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    tableMembers.addCell(header);
                });
    }


}