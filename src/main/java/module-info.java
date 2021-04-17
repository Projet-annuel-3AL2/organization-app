module com.agirpourtous {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.cli;

    opens com.agirpourtous.gui;
    exports com.agirpourtous.core.models;
    exports com.agirpourtous.core.api;
    exports com.agirpourtous.gui;
    exports com.agirpourtous.gui.controllers;
    exports com.agirpourtous;
}