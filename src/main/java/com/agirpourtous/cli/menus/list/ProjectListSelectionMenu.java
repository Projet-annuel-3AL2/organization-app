package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;

import java.util.stream.Collectors;

public class ProjectListSelectionMenu extends ListSelectionMenu {

    public ProjectListSelectionMenu(CLILauncher launcher) {
        super(launcher);
    }

    @Override
    protected void loadEntityList() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        launcher.getClient().getUserService()
                .getProjects()
                .collect(Collectors.toList())
                .doOnError(Throwable::printStackTrace)
                .doOnSuccess(projects -> {
                    for (Project project : projects) {
                        System.out.println(project);
                        actions.add(new ListAction(project.getName()) {
                            @Override
                            public Entity getEntity() {
                                return project;
                            }
                        });
                    }
                })
                .block();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
}
