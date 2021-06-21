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
    protected final APIClient client;
    private final Document document;
    private final Font titleFont;
    private final Font textFont;

    protected PdfGenerator(APIClient client) {
        this.document = new Document();
        this.client = client;
        this.titleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 24, BaseColor.BLACK);
        this.textFont = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
    }

    public File generatePdf() throws DocumentException, IOException {
        File file = getFile();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        generateDocument();
        document.close();
        return file;
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
