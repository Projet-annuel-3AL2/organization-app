package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.gui.controllers.elements.ProjectElement;
import com.agirpourtous.gui.controllers.popups.CreateProjectPopup;
import com.agirpourtous.gui.controllers.popups.CreateUserPopup;
import com.agirpourtous.gui.plugin.PluginMenuController;
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
    private HashMap<String, ProjectElement> projects;

    public MainMenuController(Controller controller) {
        super("main_menu", controller);
    }

    public MainMenuController(APIClient client) {
        super("main_menu", client);
    }

    @FXML
    public void initialize() {
        if (client.getUser() == null) {
            Platform.runLater(() -> new ConnexionController(this));
            return;
        }
        this.projects = new HashMap<>();
        this.usernameLabel.setText(client.getUser().getUsername());
        client.getUserService()
                .getProjects()
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
        try {
            new CreateUserPopup(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onCreateProjectClick() {
        try {
            new CreateProjectPopup(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onDisconnectClick() {
        new ConnexionController(this);
    }

    private void addProject(Project project) {
        if (!projects.containsKey(project.getId())) {
            try {
                projects.put(project.getId(), new ProjectElement(this, projectsHBox, project));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            projects.get(project.getId()).updateProject(project);
        }
    }

    private void removeProject(String id) {
        projects.get(id).close();
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
