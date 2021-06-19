package com.agirpourtous.core.generatePDF;

import com.agirpourtous.core.models.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

public class GeneratePDF {

    public static void generatePDF(Project proj) {
        // TODO : A Supprimer
        Project project = getFixtureProject();

        Document document = new Document();

        try {

            String pdfName = project.getName() + "_" + UUID.randomUUID()+".pdf";
            System.out.println("Nom du fichierPDF : " + pdfName);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./pdf/"+ pdfName));
            Font fontTitre = FontFactory.getFont(FontFactory.COURIER_BOLD, 24, BaseColor.BLACK);
            Font fontTitre2 = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, BaseColor.BLACK);

            Paragraph vide = new Paragraph(" ");

            document.open();

            Chunk chunkTitre = new Chunk("Nom du Projet : " + project.getName(), fontTitre);

            Chunk chunkAdmin = new Chunk("List des Admins : ", fontTitre2);
            PdfPTable tableAdmin = new PdfPTable(4);
            addTableUserHeader(tableAdmin);
            project.getAdmins().forEach(user -> addRowsMember(tableAdmin, user));

            Chunk chunkMembres = new Chunk("List des membres : ", fontTitre2);
            PdfPTable tableMembers = new PdfPTable(4);

            addTableUserHeader(tableMembers);
            project.getMembers().forEach(user -> addRowsMember(tableMembers, user));

            Paragraph p1 = new Paragraph();
            p1.add(chunkTitre);
            Paragraph p2 = new Paragraph();
            p2.add(chunkAdmin);
            p2.add(tableAdmin);

            Paragraph p3 = new Paragraph();
            p3.add(chunkMembres);
            p3.add(tableMembers);

            document.add(vide);
            document.add(p1);
            document.add(vide);
            document.add(p2);
            document.add(vide);
            document.add(p3);
            document.add(vide);

            Chunk chunkListTicket = new Chunk("Liste des tickets : ", fontTitre2);
            document.add(chunkListTicket);
            document.add(vide);
            project.getTickets().forEach(ticket -> addTicket(document, ticket));

            writer.close();

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void addTicket(Document document, Ticket ticket) {
        Font fontText = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        Font fontTextBold = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLACK);
        Paragraph title = new Paragraph( "  Titre : " +ticket.getTitle(), fontTextBold);
        Paragraph creator = new Paragraph(  "       Créateur : " +ticket.getCreator().getUsername(), fontText);
        Paragraph assignee = new Paragraph( "       Assignee : " +ticket.getAssignee().getUsername(), fontText);
        Paragraph status = new Paragraph(   "       Status : " +ticket.getStatus(), fontText);
        Paragraph description = new Paragraph(  "       Description : " , fontText);
        Paragraph descriptionText = new Paragraph(ticket.getDescription(), fontText);
        Paragraph creationDate = new Paragraph( "       Date de création : " + formatDate(ticket.getCreationDate()), fontText);
        Paragraph updateDate = new Paragraph(   "       Date de mise à jour : : " + formatDate(ticket.getCreationDate()), fontText);
        Paragraph endDate = new Paragraph(  "       Date de fin : " + formatDate(ticket.getCreationDate()), fontText);
        Paragraph estimatedDuration = new Paragraph(    "       Durée estimé : " + ticket.getEstimatedDuration(), fontText);
        Paragraph priority = new Paragraph( "       Priorité : " + ticket.getPriority(), fontText);
        Paragraph listComment = new Paragraph(  "       List des Commentaires : ", fontText);
        Paragraph vide = new Paragraph(" ");

        float [] pointColumnWidths = {150F, 300F, 150F};
        PdfPTable tableComment = new PdfPTable(pointColumnWidths);
        addTableCommentHeader(tableComment);
        ticket.getComments().forEach(comment -> addRowsComment(tableComment, comment));

        try {
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
            document.add(descriptionText);
            document.add(listComment);
            document.add(vide);
            document.add(tableComment);
            document.add(vide);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private static void addRowsComment(PdfPTable tableComment, Comment comment) {
        addCell(tableComment, comment.getUser().getUsername());
        addTextCell(tableComment, comment.getText());
        addCell(tableComment, formatDate(comment.getCreationDate()));
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

    private static void addTextCell(PdfPTable table, String data) {
        PdfPCell cell = new PdfPCell(new Phrase(data));
        cell.setHorizontalAlignment(Element.ALIGN_TOP);
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

    private static String formatDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    // TODO: A Supprimer
    private static Project getFixtureProject() {
        ArrayList<User> admins = new ArrayList<>();
        ArrayList<User> members = new ArrayList<>();
        ArrayList<Ticket> tickets = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();

        User userAdmin = new User(true, "Username", "lastname", "firstname", "jesuisunmail@gmail.com");
        User user = new User(false, "Username", "lastname", "firstname", "mail");
        Comment comment = new Comment(null, "1", user, "je suis un text de commentaire je suis un text de commentaire je suis un text de commentaire je suis un text de commentaire je suis un text de commentaire je suis un text de commentaire je suis un text de commentaire je suis un text de commentaire je suis un text de commentaire", new Date("Wed, 4 Jul 2001 12:08:56"));
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        Ticket ticket = new Ticket(comments,"1",null, "1", user,"1", userAdmin, "ticketTitle", "je suis une description de ticket je suis une description de ticket je suis une description de ticket je suis une description de ticket", TicketStatus.OPEN, new Date("Wed, 4 Jul 2001 12:08:56"), new Date("Wed, 4 Jul 2001 12:08:56"), new Date("Wed, 4 Jul 2001 12:08:56"), 3.5F, 5 );

        tickets.add(ticket);
        tickets.add(ticket);
        tickets.add(ticket);
        tickets.add(ticket);

        admins.add(userAdmin);
        admins.add(userAdmin);

        members.add(user);
        members.add(user);
        members.add(user);
        members.add(user);

        return new Project("Projet_Test_PDF", admins, members, tickets);
    }


}