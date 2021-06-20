package com.agirpourtous.core.pdf;

import com.agirpourtous.core.models.User;
import com.itextpdf.text.Document;

public class UserPdfGenerator extends PdfGenerator {
    private final User user;
    public UserPdfGenerator(User user) {
        super();
        this.user = user;
    }

    @Override
    public Document generateDocument(Document document) {
        return null;
    }
}
