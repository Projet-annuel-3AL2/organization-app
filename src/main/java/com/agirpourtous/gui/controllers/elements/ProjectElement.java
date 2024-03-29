package com.agirpourtous.gui.controllers.elements;

import com.agirpourtous.core.models.Project;
import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.ProjectDetailsController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ProjectElement extends Element {
    @FXML
    public Label titleLabel;
    @FXML
    public Label membersCountLabel;
    @FXML
    public Label activeTicketsCountLabel;
    private Project project;

    public ProjectElement(Controller controller, Pane parent, Project project) throws IOException {
        super("project_element", controller, parent);
        this.project = project;
        titleLabel.setText(project.getName());
        membersCountLabel.setText("Members: " + project.getMembers().size());
        activeTicketsCountLabel.setText("Tickets: " + project.getTickets().size());
    }

    @FXML
    public void onProjectSelect() {
        controller.setActive(false);
        new ProjectDetailsController(controller.getClient(), controller, project);
    }

    public void updateProject(Project project) {
        this.project = project;
    }
}
