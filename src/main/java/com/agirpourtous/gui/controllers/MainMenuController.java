package com.agirpourtous.gui.controllers;

import com.agirpourtous.core.api.APIClient;
import com.agirpourtous.core.models.Project;
import com.agirpourtous.gui.controllers.elements.ProjectElementController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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

    public MainMenuController(APIClient client, Stage stage) {
        super("main_menu", client, stage);
        if (client.getConnexion().getUser() != null)
            this.usernameLabel.setText(client.getConnexion().getUser().getUsername());
        addProject(new Project());
        client.getProjects().forEach(this::addProject);
        /*if(client.getConnexion().getUser().isAdmin()){
            adminPane.setVisible(true);
        }*/
    }

    private void addProject(Project project) {
        try {
            new ProjectElementController(this, projectsHBox, project);
        } catch (IOException e) {
            e.printStackTrace();
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
        new ConnexionController(client, stage);
    }
}
