package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.menus.list.ProjectAdminListMenu;
import com.agirpourtous.cli.menus.list.ProjectMemberListMenu;
import com.agirpourtous.cli.menus.list.ProjectNonAdminListMenu;
import com.agirpourtous.cli.menus.list.ProjectNonMemberListMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;

public class ProjectUsersMenu extends Menu {
    public ProjectUsersMenu(APIClient client, Project project) {
        super("Gestion des utilisateurs du projet " + project.getName());
        addAction(new Action("Ajouter un utilisateur au projet") {
            @Override
            public void execute() {
                client.getProjectService()
                        .addMember(project.getId(), new ProjectNonMemberListMenu(client, project).startList().getId())
                        .doOnTerminate(() -> new ProjectMenu(client, project))
                        .block();
            }
        });
        addAction(new Action("Retirer un utilisateur du projet") {
            @Override
            public void execute() {
                client.getProjectService()
                        .removeMember(project.getId(), new ProjectMemberListMenu(client, project).startList().getId())
                        .doOnTerminate(() -> new ProjectMenu(client, project))
                        .block();
            }
        });
        addAction(new Action("Ajouter un administrateur au projet") {
            @Override
            public void execute() {
                client.getProjectService()
                        .removeAdmin(project.getId(), new ProjectNonAdminListMenu(client, project).startList().getId())
                        .doOnTerminate(() -> new ProjectMenu(client, project))
                        .block();
            }
        });
        addAction(new Action("Retirer un administrateur du projet") {
            @Override
            public void execute() {
                client.getProjectService()
                        .removeAdmin(project.getId(), new ProjectAdminListMenu(client, project).startList().getId())
                        .doOnTerminate(() -> new ProjectMenu(client, project))
                        .block();
            }
        });
        start();
    }
}
