package com.agirpourtous.cli.controller;

import com.agirpourtous.cli.menus.HomePageMenu;
import com.agirpourtous.cli.menus.ProjectMenu;
import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.Ticket;
import com.agirpourtous.core.models.User;

import java.util.List;
import java.util.Scanner;


public class ProjectController {
    private final static Scanner SCANNER = new Scanner(System.in);

    // TODO : Trouver comment afficher showAllProject()
    public void showAllProject(APIClient client) {
        System.out.println("List of all Project : ");
        client.getProjectService().findAll();

        new ProjectMenu(client);
    }

    // TODO : Finir CreateProject
    public void createProject(APIClient client) {

        boolean loopBreak = false;
        do {
            System.out.println("Insert a name for your project : ");

            String projectName = null;
            while (projectName == null) {
                projectName = SCANNER.next();
            }

            List<User> members = askListOfUser(client);
            List<User> admin = askListOfAdmin(client);


            Project project = new Project();

//            if (client.getProjectService().create(project)){
//                System.out.println("Project Create with success");
//                loopBreak = true;
//            }else{
//                System.out.println("Problem encounter during process of create a project \n");
//            }
        }while (!loopBreak );
        new ProjectMenu(client);
    }

    //TODO : askListOfAdmin
    private List<User> askListOfAdmin(APIClient client) {

        return null;
    }

    //TODO : askListOfUser
    private List<User> askListOfUser(APIClient client) {
        return null;
    }

    //TODO : updateProject
    public void updateProject(APIClient client) {

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


        }catch (Error error){
            System.out.println("The project with the id : " + idProject + " doesn't exit or Server fail");
        }

        new ProjectMenu(client);
    }

    // TODO : Trouver Comment Afficher findProjectById(APIClient client)
    public void findProjectById(APIClient client) {
        System.out.println("Insert Id of a project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }
        try {
            client.getProjectService().getAdmins(idProject);

            new ProjectMenu(client);

        }catch (Error error){
            System.out.println("The project with the id : " + idProject + " doesn't exit or Server fail");
        }

        try {
            client.getProjectService().findById(idProject);


        }catch (Error error){
            System.out.println("The project with the id : " + idProject + " doesn't exit or Server fail");
        }
        new ProjectMenu(client);
    }

    // TODO : Trouver Comment Afficher MembersWithIdProject(APIClient client)
    public void getMembersWithIdProject(APIClient client) {

        System.out.println("Insert Id of a project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        try {
            client.getProjectService().getMembers(idProject);
        }catch (Error error){
            System.out.println("The project with the id : " + idProject + " doesn't exit or Server fail");
        }
        new ProjectMenu(client);
    }

    // TODO : Trouver Comment Afficher getAdminsWithIdProject(APIClient client)
    public void getAdminsWithIdProject(APIClient client) {

        System.out.println("Insert Id of a project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        try {
            client.getProjectService().getAdmins(idProject);
        }catch (Error error){
            System.out.println("The project with the id : " + idProject + " doesn't exit or Server fail");
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

        }catch (Error error){
            System.out.println("Error");
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

        }catch (Error error){
            System.out.println("Error");
        }

        new ProjectMenu(client);
    }

    // TODO : Trouver Comment Afficher GetAllTicketWithIdProject(APIClient client)
    public void GetAllTicketWithIdProject(APIClient client) {

        System.out.println("Insert Id of a project : ");

        String idProject = null;
        while (idProject == null) {
            idProject = SCANNER.next();
        }

        try {
            client.getProjectService().getTickets(idProject);
        }catch (Error error){
            System.out.println("The project with the id : " + idProject + " doesn't exit or Server fail");
        }
        new ProjectMenu(client);
    }

    // TODO : Finir Inserer un ticket dans un projet
    public void addTicketWithIdTicketAndIdProject(APIClient client) {
        
        String idProject = null;
        // TODO : Fonction pour récupérer le creatorId de l'user Actuel
        String creatorId = "1";
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

        while (estimatedDuration > 0) {
            estimatedDuration = SCANNER.nextFloat();
        }

        System.out.println("Insert priority (number) : ");

        while (priority > 0) {
            priority = SCANNER.nextFloat();
        }

        // TODO : Creer le ticket avec les information récupéré
        Ticket ticket = new Ticket();

        try {
            client.getProjectService().addTicket(idProject, ticket);

        }catch (Error error){
            System.out.println("Error");
        }

        new ProjectMenu(client);
    }
}
