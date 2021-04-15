module com.agirpourtous {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.cli;

    opens com.agirpourtous to javafx.fxml;
    exports com.agirpourtous;
    exports com.agirpourtous.gui to javafx.graphics;
}