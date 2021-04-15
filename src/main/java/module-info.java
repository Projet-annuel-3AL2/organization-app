module com.agirpourtous {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.cli;

    opens com.agirpourtous.gui to javafx.fxml;
    exports com.agirpourtous.gui to javafx.graphics;
    exports com.agirpourtous;
}