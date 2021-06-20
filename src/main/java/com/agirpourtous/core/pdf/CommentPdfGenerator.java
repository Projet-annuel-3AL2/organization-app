package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;
import com.agirpourtous.core.models.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import java.text.SimpleDateFormat;

public class CommentPdfGenerator extends PdfGenerator {
    private final Comment comment;

    public CommentPdfGenerator(APIClient client, Comment comment) {
        super(client);
        this.comment = comment;
    }

    @Override
    public void generateDocument(Document document) throws DocumentException {
        addCreator(document);
        document.add(new Paragraph("Commentaire: " + comment.getText(), getTextFont()));
        document.add(new Paragraph("Date de création: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(comment.getCreationDate()), getTextFont()));
        document.add(new Paragraph(" "));
    }

    private void addCreator(Document document) throws DocumentException {
        User user = client.getUserService()
                .findById(comment.getUserId())
                .block();
        if (user == null) {
            return;
        }
        document.add(new Paragraph("Créateur: " + user.getUsername(), getTextFont()));
    }
}
