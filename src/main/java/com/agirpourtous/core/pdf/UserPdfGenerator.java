package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.User;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class UserPdfGenerator extends PdfGenerator {
    private final User user;
    public UserPdfGenerator(APIClient client, User user) {
        super(client);
        this.user = user;
    }

    @Override
    public void generateDocument(Document document) throws DocumentException {
        document.add(new Paragraph("Nom d'utilisateur: " + user.getUsername(), getTextFont()));
        document.add(new Paragraph( "Prénom: " + user.getFirstname(), getTextFont()));
        document.add(new Paragraph( "Nom: " + user.getLastname(), getTextFont()));
        document.add(new Paragraph( "Email: " + user.getMail(), getTextFont()));
        document.add(new Paragraph(" "));
    }
}
