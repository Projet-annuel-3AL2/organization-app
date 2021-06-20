package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.User;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class UserPdfGenerator extends PdfGenerator {
    private final User user;
    public UserPdfGenerator(APIClient client, User user) throws DocumentException {
        super(client);
        this.user = user;
        getDocument().add(new Chunk(user.getUsername(), getTitleFont()));
    }

    @Override
    public Document generateDocument(Document document) throws DocumentException {
        document.add(new Paragraph(user.getUsername(), getTextFont()));
        document.add(new Paragraph(user.getFirstname(), getTextFont()));
        document.add(new Paragraph(user.getLastname(), getTextFont()));
        document.add(new Paragraph(user.getMail(), getTextFont()));
        return document;
    }
}
