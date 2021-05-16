package com.agirpourtous.cli.controller;

import com.agirpourtous.cli.menus.ProjectMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.api.requests.AddProjectRequest;
import com.agirpourtous.core.api.requests.AddTicketRequest;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.TicketStatus;
import com.agirpourtous.core.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectController {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Show show = new Show();

    public void showAllProject(APIClient client) {
        System.out.println("List of all Project : ");

        client.getProjectService().findAll().subscribe(show::showProject);
;
        new ProjectMenu(client);
    }

    public void createProject(APIClient client) {

        System.out.println("Insert a name for your project : ");

        String projectName = null;
        while (projectName == null) {
            projectName = SCANNER.next();
        }

        AddProjectRequest addProjectRequest = new AddProjectRequest(projectName);

        try {

            Project project = client.getProjectService().create(addProjectRequest).block();
            if (project != null){
                client.getProjectService().addAdmin(project.getId(),client.getUser().getId());
            }else{
                System.out.println("An Error occur while creating a new Project");
            }
        }catch (Exception e){
            System.out.println("An Error occur while creating a new Project");
        }

        new ProjectMenu(client);
    }

    public void updateProject(APIClient client) {
        System.out.println("Insert id of project you want to update : ");

        String projectId = null;
        while (projectId == null) {
            projectId = SCANNER.next();
        }

        try {
            Project project = client.getProjectService().findById(projectId).block();
            if (project != null){

                if (isAdminProject(client, project)){

                    System.out.println("Insert name (enter to keep " + project.getName() +" ) : ");
                    String name = SCANNER.next();
                    if (name == null){
                        name = project.getName();
                    }

                    AddProjectRequest addProjectRequest = new AddProjectRequest(name);
                    try{
                        client.getProjectService().update(projectId, addProjectRequest).block();
                    }catch (Exception e){
                        System.out.println("A problem occur during process : updateProject");
                    }
                }else{
                    System.out.println("You don't have permission to update the project");
                }
            }
        }catch (Exception e){
            System.out.println("There is no project with this id : ");
        }

        new ProjectMenu(client);
    }


    public void addTicketWithIdTicketAndIdProject(APIClient client) {

        String idProject = null;
        String creatorId = client.getUser().getId();
        String assigneId = null;
        String title = null;
        String description = null;
        float estimatedDuration = -1;
        int priority = -1;

        System.out.println("Insert id of the Project : ");

        while (idProject == null) {
            idProject = SCANNER.next();
        }

        Project project = client.getProjectService().findById(idProject).block();
        if (project != null){

            if (isAdminProject(client, project) || isMemberProject(client, project)){

                System.out.println("Insert id of the user you want to assign ticket : ");

                while (assigneId == null) {
                    assigneId = SCANNER.next();
                }

                System.out.println("Insert the title of your ticket : ");

                while (title == null) {
                    title = SCANNER.next();
                }

                System.out.println("Insert description : ");

                while (description == null) {
                    description = SCANNER.next();
                }

                System.out.println("Insert estimate duration : ");

                while (estimatedDuration < 0) {
                    estimatedDuration = SCANNER.nextFloat();
                }

                System.out.println("Insert priority (number) : ");

                while (priority < 0) {
                    priority = SCANNER.nextInt();
                }

                AddTicketRequest addTicketRequest = new AddTicketRequest(idProject,creatorId,assigneId,title,description,estimatedDuration, priority, TicketStatus.OPEN);

                try {
                    client.getProjectService().addTicket(idProject, addTicketRequest).block();

                }catch (Exception e){
                    System.out.println("An Error occur while creating a new Project");
                }
            }else{
                System.out.println("You can't add ticket to this project");
            }
        }else{
            System.out.println("There is no project with this id");
        }

        new ProjectMenu(client);
    }

    public void deleteProject(APIClient client) {

        System.out.println("Insert id of the Project you want to delete : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        try {
            client.getProjectService().delete(idProject);
        }catch (Exception e){
            System.out.println("The project with the id : " + idProject + " doesn't exit or Server fail");
        }

        new ProjectMenu(client);
    }

    public void findProjectById(APIClient client) {
        System.out.println("Insert Id of a project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        try{

            Project project = client.getProjectService().findById(idProject).block();
            if (project != null){
                show.showProject(project);
            }else{
                System.out.println("The project with the id : " + idProject + " doesn't exit or Server fail");
            }
        }catch (Exception e){
            System.out.println("The project with the id : " + idProject + " doesn't exit or Server fail");
        }

        new ProjectMenu(client);
    }

    public void getMembersWithIdProject(APIClient client) {

        System.out.println("Insert Id of a project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        System.out.println("------ List of user for project id " + idProject + " :");
        try {
            client.getProjectService().getMembers(idProject).subscribe(show::showUser);
        }catch (Exception e){
            System.out.println("There is no project with the given id");
        }

        new ProjectMenu(client);
    }

    public void getAdminsWithIdProject(APIClient client) {

        System.out.println("Insert Id of a project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        System.out.println("------ List of Admin of project id " + idProject + " :");
        try {

            client.getProjectService().getAdmins(idProject).subscribe(show::showUser);
        }catch (Exception e){
            System.out.println("There is no project with the given id");
        }

        new ProjectMenu(client);
    }

    public void removeMemberWithIdProjectAndIdMember(APIClient client) {

        System.out.println("Insert id of the Project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        System.out.println("Insert id of the Member you want to remove : ");

        String userId = null;
        while (userId == null) {
            userId = SCANNER.next();
        }

        try {
            client.getProjectService().removeMember(idProject, userId);

        }catch (Exception e){
            System.out.println("Error while removing Member");
        }

        new ProjectMenu(client);
    }

    public void removeAdminWithIdProject(APIClient client) {

        System.out.println("Insert id of the Project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        System.out.println("Insert id of the admin you want to remove : ");

        String userId = null;
        while (userId == null) {
            userId = SCANNER.next();
        }

        try {
            client.getProjectService().removeAdmin(idProject, userId);

        }catch (Exception e){
            System.out.println("Error while removing admin");
        }

        new ProjectMenu(client);
    }

    public void GetAllTicketWithIdProject(APIClient client) {

        System.out.println("Insert Id of a project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        System.out.println("------ List of all ticket for project id " + idProject + " :");
        try {
            client.getProjectService().getTickets(idProject).subscribe(show::showTicket);
        }catch (Exception e){
            System.out.println("There is no project with the given id");
        }

        new ProjectMenu(client);
    }


    private List<User> askListOfUser(APIClient client) {
        List<User> listOfUser = new ArrayList<User>();
        String userId = "";

        while (!userId.equals("stop")){

            System.out.println("------- Choose an User and input his id");
            client.getUserService().findAll().subscribe(user -> {
                System.out.println("------ username : " + user.getUsername() + "\n" +
                        "------ User Id : " + user.getId() + "\n" +
                        "--------");
            });
            System.out.println("------- (input 'stop' to stop choosing User)");

            userId = SCANNER.next();

            if (userId.equals("stop")){
                break;
            }

            boolean isNotDuplicate = true;
            if (listOfUser.size() > 0){

                for (int i = 0; i < listOfUser.size(); i++){
                    if (listOfUser.get(i).getId().equals(userId)) {
                        isNotDuplicate = false;
                        break;
                    }
                }
            }

            if (isNotDuplicate){
                try {

                    User user = client.getUserService().findById(userId).block();
                    if(user != null){
                        listOfUser.add(user);
                    }else{
                        System.out.println("Id of user doesn't exist");
                    }
                }catch (Exception e){
                    System.out.println("Id of user doesn't exist");
                }

            }else{
                System.out.println("User already select");
            }
        }

        return listOfUser;
    }

    private boolean isAdminProject(APIClient client, Project project) {
        final boolean[] isGranted = new boolean[1];
        client.getProjectService().getAdmins(project.getId()).subscribe(user -> {
            if(user.getId().equals(client.getUser().getId())){
                isGranted[0] = true;
            }
        });
        return isGranted[0] ;
    }

    private boolean isMemberProject(APIClient client, Project project) {
        final boolean[] isGranted = new boolean[1];
        client.getProjectService().getMembers(project.getId()).subscribe(user -> {
            if(user.getId().equals(client.getUser().getId())){
                isGranted[0] = true;
            }
        });
        return isGranted[0] ;
    }
}
