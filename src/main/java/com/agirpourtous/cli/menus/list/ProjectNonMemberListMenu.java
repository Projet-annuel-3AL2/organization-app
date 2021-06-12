package com.agirpourtous.cli.menus.list;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Entity;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.stream.Collectors;

public class ProjectNonMemberListMenu extends ListSelectionMenu {
    private final Project project;

    public ProjectNonMemberListMenu(APIClient client, Project project) {
        super(client);
        this.project = project;
    }

    @Override
    protected void loadEntityList() {
        client.getUserService()
                .findAll()
                .collect(Collectors.toList())
                .subscribe(users -> client.getProjectService()
                        .getMembers(project.getId())
                        .collect(Collectors.toList())
                        .subscribe(members -> {
                            users.removeAll(members);
                            for (User user : users) {
                                actions.add(new ListAction(user.getUsername()) {
                                    @Override
                                    public Entity getEntity() {
                                        return user;
                                    }
                                });

                            }
                        }));
    }
}
