package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    public void generatePdf() throws DocumentException, IOException {
        File file = getFile();
        PdfWriter.getInstance(generateDocument(), new FileOutputStream(file));
    }

    public Document generateDocument() throws DocumentException {
        return generateDocument(this.document);
    }

    protected abstract Document generateDocument(Document document) throws DocumentException;

    private File getFile() throws IOException {
        File file = new File("./pdf/details_pdf.pdf");
        Files.createDirectories(Path.of("./pdf"));
        int increase = 0;
        while (file.exists()) {
            increase++;
            file = new File("./pdf/details_pdf(" + increase + ").pdf");
        }
        return file;
    }

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
