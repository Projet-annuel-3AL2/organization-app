package com.agirpourtous.core.pdf;

import com.agirpourtous.core.models.Project;
import com.itextpdf.text.Document;

public class ProjectPdfGenerator extends PdfGenerator {
    private final Project project;
    public ProjectPdfGenerator(Project project) {
        super();
        this.project = project;
    }

    @Override
    public Document generateDocument(Document document) {
        return null;
    }
}
