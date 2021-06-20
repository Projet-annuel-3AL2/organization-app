package com.agirpourtous.core.pdf;

import com.itextpdf.text.Document;

public abstract class PdfGenerator {
    private final Document document;

    protected PdfGenerator() {
        document = new Document();
    }

    public void generatePdf(){
    }

    public Document generateDocument(){
        return generateDocument(this.document);
    }
    public abstract Document generateDocument(Document document);
}
