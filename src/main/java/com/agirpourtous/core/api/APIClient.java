package com.agirpourtous.core.api;

import com.agirpourtous.core.models.Project;
import com.agirpourtous.core.models.User;

import java.util.ArrayList;

public class APIClient {
    private final APIConnexion connexion;
    private final ArrayList<User> users;
    private final ArrayList<Project> projects;


    public APIClient(){
        this.connexion = new APIConnexion();
        this.users = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public APIClient(APIConnexion connexion) {
        this.connexion = connexion;
        this.users = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public APIConnexion getConnexion() {
        return connexion;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void addProjects(Project project) {
        this.projects.add(project);
    }
}
