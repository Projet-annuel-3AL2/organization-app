package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.gui.controllers.elements.ProjectElementController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import reactor.retry.Repeat;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MainMenuController extends Controller {
    private final HashMap<String, ProjectElementController> projects;
    @FXML
    public Label usernameLabel;
    @FXML
    public Button createUserButton;
    @FXML
    public Button createProjectButton;
    @FXML
    public Button disconnectButton;
    @FXML
    public Label projectLabel;
    @FXML
    public HBox projectsHBox;
    @FXML
    public VBox adminPane;

    public MainMenuController(APIClient client, Controller controller) {
        super("main_menu", controller);
        this.usernameLabel.setText(client.getUser().getUsername());
        this.projects = new HashMap<>();
        client.getProjectService()
                .findAll()
                .collect(Collectors.toList())
                .repeatWhen(Repeat.onlyIf(repeatContext -> isActive)
                        .fixedBackoff(Duration.ofSeconds(10)))
                .subscribe(projects -> Platform.runLater(() -> setProjects(projects)));
        if (client.getUser().isAdmin()) {
            adminPane.setVisible(true);
        }
    }

    @FXML
    public void onCreateUserClick() {

    }

    @FXML
    public void onCreateProjectClick() {

    }

    @FXML
    public void onDisconnectClick() {
        new ConnexionController(client, this);
    }

    private void addProject(Project project) {
        if (!projects.containsKey(project.getId())) {
            try {
                projects.put(project.getId(), new ProjectElementController(this, projectsHBox, project));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            projects.get(project.getId()).updateProject(project);
        }
    }

    private void removeProject(String id) {
        projects.get(id).remove();
        projects.remove(id);
    }

    private void removeDeletedProjects(List<Project> projectList) {
        List<String> receivedIds = projectList.stream().map(Project::getId).collect(Collectors.toList());
        List<String> elementsDeleted = new ArrayList<>(projects.keySet());
        elementsDeleted.removeAll(receivedIds);
        elementsDeleted.forEach(this::removeProject);
    }

    private void setProjects(List<Project> projectList) {
        removeDeletedProjects(projectList);

        projectList.forEach(this::addProject);
    }
}
