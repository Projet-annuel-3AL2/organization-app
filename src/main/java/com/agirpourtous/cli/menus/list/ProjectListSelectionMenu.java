package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;

import java.util.stream.Collectors;

public class ProjectListSelectionMenu extends ListSelectionMenu {

    public ProjectListSelectionMenu(CLILauncher launcher) {
        super(launcher, "Sélectionnez un projet");
    }

    @Override
    protected void loadEntityList() {
        launcher.getClient().getUserService()
                .getProjects()
                .collect(Collectors.toList())
                .doOnSuccess(projects -> {
                    for (Project project : projects) {
                        addAction(new ListAction(project.getName()) {
                            @Override
                            public Entity getEntity() {
                                return project;
                            }
                        });
                    }
                })
                .block();
    }
}
