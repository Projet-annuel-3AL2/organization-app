package com.agirpourtous.core.pdf;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class ProjectPdfGenerator extends PdfGenerator {
    private final Project project;

    public ProjectPdfGenerator(APIClient client, Project project) throws DocumentException {
        super(client);
        this.project = project;
        getDocument().add(new Chunk(project.getName(), getTitleFont()));
    }

    @Override
    public Document generateDocument(Document document) throws DocumentException {
        document.add(new Chunk("Nom du Projet:" + project.getName(), getTextFont()));
        document.add(new Chunk("List des Admins:", getTitleFont()));
        for (User user : project.getAdmins())
            new UserPdfGenerator(client, user).generateDocument(document);
        document.add(new Chunk("List des utilisateurs:", getTitleFont()));
        for (User user : project.getMembers())
            new UserPdfGenerator(client, user).generateDocument(document);

        return document;
    }
}
