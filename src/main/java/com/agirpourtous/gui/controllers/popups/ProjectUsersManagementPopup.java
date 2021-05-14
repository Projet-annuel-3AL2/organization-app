package com.agirpourtous.gui.controllers.popups;

import com.agirpourtous.core.models.User;
import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.ProjectDetailsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.stream.Collectors;

public class ProjectUsersManagementPopup extends Popup {
    public Label utilisateur;
    public ChoiceBox<User> usersNotMemberList;
    public ChoiceBox<User> usersMemberList;
    public ComboBox<User> usersAdminList;
    public ComboBox<User> usersNotAdminList;

    public ProjectUsersManagementPopup(Controller controller) throws IOException {
        super("Management des utilisateurs", "project_users_management_popup", controller);
    }

    @FXML
    public void initialize() {
        StringConverter<User> stringConverter = new StringConverter<>() {
            @Override
            public String toString(User object) {
                if (object != null)
                    return object.getUsername();
                return "a";
            }

            @Override
            public User fromString(String string) {
                return null;
            }
        };
        usersMemberList.setItems(getMembers());
        usersMemberList.setConverter(stringConverter);

        usersNotMemberList.setConverter(stringConverter);

        usersAdminList.setItems(getAdmins());
        usersAdminList.setConverter(stringConverter);

        usersNotAdminList.setConverter(stringConverter);
    }

    private ObservableList<User> getMembers() {
        ObservableList<User> observableList = FXCollections.observableArrayList();
        controller.getClient()
                .getProjectService()
                .getMembers(((ProjectDetailsController) controller).getProject().getId())
                .collect(Collectors.toList())
                .block();
        return observableList;
    }

    private ObservableList<User> getAdmins() {
        ObservableList<User> observableList = FXCollections.observableArrayList();
        controller.getClient()
                .getProjectService()
                .getAdmins(((ProjectDetailsController) controller).getProject().getId())
                .collect(Collectors.toList())
                .block();
        return observableList;
    }

    public void onAddUsersClick() {
    }

    public void onRemoveUsersClick() {
    }

    public void onAddAdminClick() {
    }

    public void onRemoveAdminClick() {
    }
}
