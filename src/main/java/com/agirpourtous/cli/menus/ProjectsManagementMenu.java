package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.forms.AddProjectForm;
import com.agirpourtous.cli.menus.list.ProjectListSelectionMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;

public class ProjectsManagementMenu extends Menu {
    protected static final int id = 0;

    public ProjectsManagementMenu(CLILauncher launcher) {
        super(launcher, "Projects menu");
        addAction(new Action("Choisir un projet") {
            @Override
            public void execute() {
                Project project = (Project) new ProjectListSelectionMenu(launcher).startList();
                if (project != null) {
                    launcher.setActiveMenu(new ProjectMenu(launcher, project));
                } else {
                    launcher.setActiveMenu(new MainMenu(launcher));
                }
            }
        });
        if (launcher.getClient().getUser().isAdmin()) {
            addAction(new Action("Ajouter un projet") {
                @Override
                public void execute() {
                    launcher.getClient().getProjectService()
                            .create(new AddProjectForm().askEntries())
                            .doOnSuccess(project -> launcher.setActiveMenu(new ProjectMenu(launcher, project)))
                            .doOnError(err -> launcher.setActiveMenu(new MainMenu(launcher)))
                            .subscribe();
                }
            });
        }
    }
}