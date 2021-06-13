package com.agirpourtous.gui.controllers.popups;

import com.agirpourtous.core.api.requests.UsersManagementRequest;
import com.agirpourtous.core.models.User;
import com.agirpourtous.gui.controllers.Controller;
import com.agirpourtous.gui.controllers.ProjectDetailsController;
import javafx.application.Platform;
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
                return "";
            }

            @Override
            public User fromString(String string) {
                return null;
            }
        };
        usersMemberList.setConverter(stringConverter);
        usersNotMemberList.setConverter(stringConverter);
        usersNotAdminList.setConverter(stringConverter);
        usersAdminList.setConverter(stringConverter);
        this.retrieveLists();
    }

    private void retrieveLists() {
        usersMemberList.getItems().setAll(getMembers());
        usersMemberList.getCheckModel().clearChecks();
        usersNotMemberList.getItems().setAll(getNotProjectMembers());
        usersNotMemberList.getCheckModel().clearChecks();
        usersAdminList.setItems(getAdmins());
        usersAdminList.getSelectionModel().clearSelection();
        usersNotAdminList.setItems(getNotProjectAdmins());
        usersNotAdminList.getSelectionModel().clearSelection();
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
        if (usersNotMemberList.getCheckModel().getCheckedItems().size() > 0) {
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
                    .doOnSuccess(response -> Platform.runLater(this::retrieveLists))
                    .subscribe();
        }
    }

    public void onRemoveUsersClick() {
        if (usersMemberList.getCheckModel().getCheckedItems().size() > 0) {
            controller.getClient()
                    .getProjectService()
                    .removeMembers(((ProjectDetailsController) controller)
                                    .getProject()
                                    .getId(),
                            new UsersManagementRequest(usersMemberList
                                    .getCheckModel()
                                    .getCheckedItems()
                                    .stream()
                                    .map(User::getId)
                                    .collect(Collectors.toList())))
                    .doOnSuccess(response -> Platform.runLater(this::retrieveLists))
                    .subscribe();
        }
    }

    public void onAddAdminClick() {
        if (usersNotAdminList.getSelectionModel().getSelectedItem() != null) {
            controller.getClient()
                    .getProjectService()
                    .addAdmin(((ProjectDetailsController) controller)
                                    .getProject()
                                    .getId(),
                            usersNotAdminList
                                    .getSelectionModel()
                                    .getSelectedItem()
                                    .getId())
                    .doOnSuccess(response -> Platform.runLater(this::retrieveLists))
                    .subscribe();
        }
    }

    public void onRemoveAdminClick() {
        if (usersAdminList.getSelectionModel().getSelectedItem() != null) {
            controller.getClient()
                    .getProjectService()
                    .removeAdmin(((ProjectDetailsController) controller)
                                    .getProject()
                                    .getId(),
                            usersAdminList
                                    .getSelectionModel()
                                    .getSelectedItem()
                                    .getId())
                    .doOnSuccess(response -> Platform.runLater(this::retrieveLists))
                    .subscribe();
        }
    }
}
