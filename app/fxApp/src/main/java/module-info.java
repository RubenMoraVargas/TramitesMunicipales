module org.una.tramites.fxapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.una.tramites.fxapp to javafx.fxml;
    exports org.una.tramites.fxapp;
}
