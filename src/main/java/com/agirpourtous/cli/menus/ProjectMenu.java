package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.pdf.ProjectPdfGenerator;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public class ProjectMenu extends Menu {

    public ProjectMenu(CLILauncher launcher, Project project) {
        super(launcher, "Menu du projet " + project.getName());
        addAction(new Action("Gérer les tickets") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new TicketManagementMenu(launcher, project));
            }
        });
        if (launcher.getClient().getUser().isAdmin()) {
            addAction(new Action("Gestion d'utilisateurs") {
                @Override
                public void execute() {
                    launcher.setActiveMenu(new ProjectUsersMenu(launcher, project));
                }
            });
            addAction(new Action("Supprimer le projet") {
                @Override
                public void execute() {
                    launcher.getClient().getProjectService()
                            .delete(project.getId())
                            .doOnError(err -> launcher.setActiveMenu(new MainMenu(launcher)))
                            .doOnTerminate(() -> launcher.setActiveMenu(new MainMenu(launcher)))
                            .subscribe();
                }
            });
        }
        addAction(new Action("Exporter sous format PDF") {
            @Override
            public void execute() {
                try {
                    new ProjectPdfGenerator(launcher.getClient(), project).generatePdf();
                } catch (DocumentException | FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Erreur lors de l'écriture du fichier PDF");
                }
            }
        });
        addAction(new Action("Retour au menu principal") {
            @Override
            public void execute() {
                launcher.setActiveMenu(new MainMenu(launcher));
            }
        });
    }
}
