package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Comment;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.text.SimpleDateFormat;

public class CommentPdfGenerator extends PdfGenerator {
    private final Comment comment;
    public CommentPdfGenerator(APIClient client, Comment comment) {
        super(client);
        this.comment = comment;
    }

    @Override
    public void generateDocument(Document document) throws DocumentException {
        document.add(new Chunk(comment.getUser().getUsername(), getTextFont()));
        document.add(new Chunk(comment.getText(), getTextFont()));
        document.add(new Chunk(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(comment.getCreationDate()), getTextFont()));
    }
}
