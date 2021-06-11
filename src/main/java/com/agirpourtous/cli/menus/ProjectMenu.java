package com.agirpourtous.cli.menus;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;

public class ProjectMenu extends Menu {
    public ProjectMenu(APIClient client, Project project) {
        super("Menu du projet " + project.getName());

    }
}
