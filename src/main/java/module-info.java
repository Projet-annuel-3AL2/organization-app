module com.agirpourtous {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.agirpourtous to javafx.fxml;
    exports com.agirpourtous;
}