package com.agirpourtous.core.pdf;

import com.agirpourtous.core.models.Ticket;
import com.itextpdf.text.Document;

public class TicketPdfGenerator extends PdfGenerator {
    private final Ticket ticket;
    public TicketPdfGenerator(Ticket ticket) {
        super();
        this.ticket = ticket;
    }

    @Override
    public Document generateDocument(Document document) {
        return null;
    }
}
