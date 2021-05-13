package com.agirpourtous.gui.controllers.popups;

import com.agirpourtous.gui.controllers.Controller;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class ProjectUsersManagementPopup extends Popup {
    public Label utilisateur;
    public ComboBox usersNonMemberList;
    public ComboBox usersMemberList;

    ProjectUsersManagementPopup(Controller controller) throws IOException {
        super("Management des utilisateurs", "project_users_management_popup", controller);
    }

    public void onAddUsersClick() {
    }

    public void onRemoveUsersClick() {
    }

    public void onCloseClick() {
    }
}
