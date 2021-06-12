package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.list.ProjectAdminListMenu;
import com.agirpourtous.cli.menus.list.ProjectMemberListMenu;
import com.agirpourtous.cli.menus.list.ProjectNonAdminListMenu;
import com.agirpourtous.cli.menus.list.ProjectNonMemberListMenu;
import com.agirpourtous.core.models.Project;

public class ProjectUsersMenu extends Menu {
    protected static final int id = 0;

    public ProjectUsersMenu(CLILauncher launcher, Project project) {
        super(launcher, "Gestion des utilisateurs du projet " + project.getName());
        addAction(new Action("Ajouter un utilisateur au projet") {
            @Override
            public void execute() {
                launcher.getClient().getProjectService()
                        .addMember(project.getId(), new ProjectNonMemberListMenu(launcher, project).startList().getId())
                        .doOnTerminate(() -> launcher.setActiveMenu(new ProjectMenu(launcher, project)))
                        .subscribe();
            }
        });
        addAction(new Action("Retirer un utilisateur du projet") {
            @Override
            public void execute() {
                launcher.getClient().getProjectService()
                        .removeMember(project.getId(), new ProjectMemberListMenu(launcher, project).startList().getId())
                        .doOnTerminate(() -> launcher.setActiveMenu(new ProjectMenu(launcher, project)))
                        .subscribe();
            }
        });
        addAction(new Action("Ajouter un administrateur au projet") {
            @Override
            public void execute() {
                launcher.getClient().getProjectService()
                        .removeAdmin(project.getId(), new ProjectNonAdminListMenu(launcher, project).startList().getId())
                        .doOnTerminate(() -> launcher.setActiveMenu(new ProjectMenu(launcher, project)))
                        .subscribe();
            }
        });
        addAction(new Action("Retirer un administrateur du projet") {
            @Override
            public void execute() {
                launcher.getClient().getProjectService()
                        .removeAdmin(project.getId(), new ProjectAdminListMenu(launcher, project).startList().getId())
                        .doOnTerminate(() -> launcher.setActiveMenu(new ProjectMenu(launcher, project)))
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
