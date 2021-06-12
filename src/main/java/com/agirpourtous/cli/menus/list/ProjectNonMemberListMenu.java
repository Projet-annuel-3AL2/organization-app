package com.agirpourtous.cli.menus.list;

import com.agirpourtous.cli.CLILauncher;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectNonMemberListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectNonMemberListMenu(CLILauncher launcher, Project project) {
        super(launcher, "Sélectionnez un utilisateur ne faisant pas partie du projet " + project.getName());
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        try {
            List<User> members = getMembersList();
            List<User> users = getUsersList();
            if (users != null && members != null) {
                users.removeAll(members);
                for (User user : users) {
                    if (user.getId().equals(launcher.getClient().getUser().getId())) {
                        continue;
                    }
                    addAction(new ListAction(user.getUsername()) {
                        @Override
                        public Entity getEntity() {
                            return user;
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<User> getUsersList() {
        return launcher.getClient().getUserService()
                .findAll()
                .collect(Collectors.toList())
                .block();
    }

    private List<User> getMembersList() {
        return launcher.getClient().getProjectService()
                .getMembers(project.getId())
                .collect(Collectors.toList())
                .block();
    }
}
