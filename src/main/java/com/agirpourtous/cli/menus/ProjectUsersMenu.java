package com.agirpourtous.cli.menus;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;

public class ProjectUsersMenu extends Menu {
    public ProjectUsersMenu(APIClient client, Project project) {
        super("Gestion des utilisateurs du projet " + project.getName());
        start();
    }
}
