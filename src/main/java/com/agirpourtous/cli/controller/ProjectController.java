package com.agirpourtous.cli.controller;

import com.agirpourtous.cli.menus.ProjectMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
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

    // TODO : Finir CreateProject
    public void createProject(APIClient client) {

        System.out.println("Insert a name for your project : ");

        String projectName = null;
        while (projectName == null) {
            projectName = SCANNER.next();
        }

        List<User> members = askListOfUser(client);
        List<User> admin = askListOfUser(client);


        Project project = new Project();

        try {

            client.getProjectService().create(project);
        }catch (Exception e){
            System.out.println("An Error occur while creating a new Project");
        }

        new ProjectMenu(client);
    }

    //TODO : updateProject
    public void updateProject(APIClient client) {



        new ProjectMenu(client);
    }

    public void addTicketWithIdTicketAndIdProject(APIClient client) {

        String idProject = null;
        String creatorId = client.getUser().getId();
        String assigneId = null;
        String title = null;
        String description = null;
        float estimatedDuration = -1;
        float priority = -1;

        System.out.println("Insert id of the Project : ");

        while (idProject == null) {
            idProject = SCANNER.next();
        }


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
            priority = SCANNER.nextFloat();
        }

        // TODO : Creer le ticket avec les information récupéré
        Ticket ticket = new Ticket();

        try {
            client.getProjectService().addTicket(idProject, ticket);

        }catch (Exception e){
            System.out.println("An Error occur while creating a new Project");
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

                // TODO : les erreurs sont pas gérer

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

}
