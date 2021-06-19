package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.generatePDF.GeneratePDF;
import com.agirpourtous.core.models.Project;

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
        // TODO : Remplacer findById() par un getFullProject()
        addAction(new Action("Export Projet en PDF") {
            @Override
            public void execute() {
                launcher.getClient().getProjectService()
                        .findById(project.getId())
                        .doOnSuccess(project1 -> {
                            GeneratePDF.generatePDF(project);
                        })
                        .doOnError(err -> {
                            System.out.println(err.toString());
                        }).doOnTerminate(() -> launcher.setActiveMenu(new MainMenu(launcher)))
                        .subscribe();
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
