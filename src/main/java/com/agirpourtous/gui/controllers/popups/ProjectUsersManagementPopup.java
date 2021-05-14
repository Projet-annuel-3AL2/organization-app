package com.agirpourtous.gui.controllers.popups;

import com.agirpourtous.core.api.requests.UsersManagementRequest;
import com.agirpourtous.core.models.User;
import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.ProjectDetailsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.util.stream.Collectors;

public class ProjectUsersManagementPopup extends Popup {
    public CheckComboBox<User> usersNotMemberList;
    public CheckComboBox<User> usersMemberList;
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
        usersMemberList.getItems().setAll(getMembers());
        usersMemberList.setConverter(stringConverter);

        usersNotMemberList.getItems().setAll(getNotProjectMembers());
        usersNotMemberList.setConverter(stringConverter);

        usersNotAdminList.setItems(getNotProjectAdmins());
        usersNotAdminList.setConverter(stringConverter);

        usersAdminList.setItems(getAdmins());
        usersAdminList.setConverter(stringConverter);

    }

    private ObservableList<User> getMembers() {
        ObservableList<User> list = FXCollections.observableArrayList(
                controller.getClient()
                        .getProjectService()
                        .getMembers(((ProjectDetailsController) controller).getProject().getId())
                        .collect(Collectors.toList())
                        .block());
        list.remove(controller.getClient().getUser());
        return list;
    }

    private ObservableList<User> getNotProjectMembers() {
        ObservableList<User> list = FXCollections.observableArrayList(
                controller.getClient()
                        .getUserService()
                        .findAll()
                        .collect(Collectors.toList())
                        .block());
        list.removeAll(usersMemberList.getItems());
        list.remove(controller.getClient().getUser());
        return list;
    }

    private ObservableList<User> getAdmins() {
        ObservableList<User> list = FXCollections.observableArrayList(
                controller.getClient()
                        .getProjectService()
                        .getAdmins(((ProjectDetailsController) controller).getProject().getId())
                        .collect(Collectors.toList())
                        .block());
        list.remove(controller.getClient().getUser());
        return list;
    }

    private ObservableList<User> getNotProjectAdmins() {
        ObservableList<User> list = FXCollections.observableArrayList(usersMemberList.getItems());
        list.removeAll(usersAdminList.getItems());
        list.remove(controller.getClient().getUser());
        return list;
    }

    public void onAddUsersClick() {
        controller.getClient()
                .getProjectService()
                .addMembers(((ProjectDetailsController) controller)
                                .getProject()
                                .getId(),
                        new UsersManagementRequest(usersNotMemberList
                                .getCheckModel()
                                .getCheckedItems()
                                .stream()
                                .map(User::getId)
                                .collect(Collectors.toList())))
                .subscribe();
    }

    public void onRemoveUsersClick() {
    }

    public void onAddAdminClick() {
    }

    public void onRemoveAdminClick() {
    }
}
