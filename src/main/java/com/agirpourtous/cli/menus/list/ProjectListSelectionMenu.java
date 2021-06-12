package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;

import java.util.stream.Collectors;

public class ProjectListSelectionMenu extends ListSelectionMenu {

    public ProjectListSelectionMenu(CLILauncher launcher) {
        super(launcher,"Sélectionnez un projet");
    }

    @Override
    protected void loadEntityList() {
        launcher.getClient().getUserService()
                .getProjects()
                .collect(Collectors.toList())
                .doOnError(Throwable::printStackTrace)
                .doOnSuccess(projects -> {
                    for (Project project : projects) {
                        System.out.println(project.getName());
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
