package com.agirpourtous.cli.menus;

import com.agirpourtous.cli.controller.ProjectController;
import com.agirpourtous.core.api.APIClient;

public class ProjectMenu extends Menu{
    private final ProjectController projectController = new ProjectController();

    public ProjectMenu(APIClient client) {
        super("Project Menu");

        addAction(new Action("Show all Project") {
            @Override
            public void execute() {
                projectController.showAllProject(client);

            }
        });

        addAction(new Action("Create a project") {
            @Override
            public void execute() {
                projectController.createProject(client);
            }
        });

        addAction(new Action("Update a project") {
            @Override
            public void execute() {
                projectController.updateProject(client);
            }
        });

        addAction(new Action("Delete ad project") {
            @Override
            public void execute() {
                projectController.deleteProject(client);
            }
        });

        addAction(new Action("Find project By ID") {
            @Override
            public void execute() {
                projectController.findProjectById(client);
            }
        });

        addAction(new Action("Get Members of a project with ID Project") {
            @Override
            public void execute() {
                projectController.getMembersWithIdProject(client);
            }
        });

        addAction(new Action("Get Admin of a project with ID Project") {
            @Override
            public void execute() {
                projectController.getAdminsWithIdProject(client);
            }
        });

        addAction(new Action("Remove member of a project") {
            @Override
            public void execute() {
                projectController.removeMemberWithIdProjectAndIdMember(client);
            }
        });

        addAction(new Action("Remove Admin of a project") {
            @Override
            public void execute() {
                projectController.removeAdminWithIdProject(client);
            }
        });

        addAction(new Action("Get All ticket of a project") {
            @Override
            public void execute() {
                projectController.GetAllTicketWithIdProject(client);
            }
        });

        addAction(new Action("Add Ticket to a project") {
            @Override
            public void execute() {
                projectController.addTicketWithIdTicketAndIdProject(client);
            }
        });

        addAction(new Action("Return HomePage") {
            @Override
            public void execute() {
                new HomePageMenu(client);
            }
        });

        // TODO : A supprimer
        addAction(new Action("get User") {
            @Override
            public void execute() {
                ProjectController.getUser(client);
            }
        });

        start();
    }
}
