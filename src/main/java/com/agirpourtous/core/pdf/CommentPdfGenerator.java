package com.agirpourtous.core.pdf;

import com.agirpourtous.core.models.Comment;
import com.itextpdf.text.Document;

public class CommentPdfGenerator extends PdfGenerator {
    private final Comment comment;
    public CommentPdfGenerator(Comment comment) {
        super();
        this.comment = comment;
    }

    @Override
    public Document generateDocument(Document document) {
        return null;
    }
}
