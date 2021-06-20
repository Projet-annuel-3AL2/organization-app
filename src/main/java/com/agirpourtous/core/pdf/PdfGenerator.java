package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public abstract class PdfGenerator {
    private final Document document;
    private final Font titleFont;
    private final Font textFont;
    protected final APIClient client;

    protected PdfGenerator(APIClient client) {
        this.document = new Document();
        this.document.open();
        this.client = client;
        this.titleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 24, BaseColor.BLACK);
        this.textFont = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
    }

    public void generatePdf() throws DocumentException, FileNotFoundException {
        File file = new File("./pdf/details_pdf.pdf");
        int increase = 1;
        while (file.exists()) {
            increase++;
            file = new File("./pdf/details_pdf(" + increase + ").pdf");
        }
        PdfWriter.getInstance(generateDocument(), new FileOutputStream(file));
    }

    public Document generateDocument() throws DocumentException {
        return generateDocument(this.document);
    }

    protected abstract Document generateDocument(Document document) throws DocumentException;

    protected Font getTitleFont() {
        return titleFont;
    }

    protected Font getTextFont() {
        return textFont;
    }

    protected Document getDocument() {
        return document;
    }
}
