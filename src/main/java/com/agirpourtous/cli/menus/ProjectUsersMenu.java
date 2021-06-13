package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.cli.menus.list.ProjectAdminListMenu;
import com.agirpourtous.cli.menus.list.ProjectMemberListMenu;
import com.agirpourtous.cli.menus.list.ProjectNonAdminListMenu;
import com.agirpourtous.cli.menus.list.ProjectNonMemberListMenu;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

public class ProjectUsersMenu extends Menu {

    public ProjectUsersMenu(CLILauncher launcher, Project project) {
        super(launcher, "Gestion des utilisateurs du projet " + project.getName());
        addAction(new Action("Ajouter un utilisateur au projet") {
            @Override
            public void execute() {
                User user = (User) new ProjectNonMemberListMenu(launcher, project).startList();
                if (user == null) {
                    return;
                }
                launcher.getClient().getProjectService()
                        .addMember(project.getId(), user.getId())
                        .doOnTerminate(() -> launcher.setActiveMenu(new ProjectMenu(launcher, project)))
                        .subscribe();
            }
        });
        addAction(new Action("Retirer un utilisateur du projet") {
            @Override
            public void execute() {
                User user = (User) new ProjectMemberListMenu(launcher, project).startList();
                if (user == null) {
                    return;
                }
                launcher.getClient().getProjectService()
                        .removeMember(project.getId(), user.getId())
                        .doOnTerminate(() -> launcher.setActiveMenu(new ProjectMenu(launcher, project)))
                        .subscribe();
            }
        });
        addAction(new Action("Ajouter un administrateur au projet") {
            @Override
            public void execute() {
                User user = (User) new ProjectNonAdminListMenu(launcher, project).startList();
                if (user == null) {
                    return;
                }
                launcher.getClient().getProjectService()
                        .removeAdmin(project.getId(), user.getId())
                        .doOnTerminate(() -> launcher.setActiveMenu(new ProjectMenu(launcher, project)))
                        .subscribe();
            }
        });
        addAction(new Action("Retirer un administrateur du projet") {
            @Override
            public void execute() {
                User user = (User) new ProjectAdminListMenu(launcher, project).startList();
                if (user == null) {
                    return;
                }
                launcher.getClient().getProjectService()
                        .removeAdmin(project.getId(), user.getId())
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
