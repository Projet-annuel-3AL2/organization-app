package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.controller.ProjectController;
import com.agirpourtous.core.api.APIClient;

public class ProjectMenu extends Menu{
    private final ProjectController projectController = new ProjectController();

    public ProjectMenu(APIClient client) {
        super("Project Menu");

        addAction(new Action("") {
            @Override
            public void execute() {

            }
        });
    }
}
