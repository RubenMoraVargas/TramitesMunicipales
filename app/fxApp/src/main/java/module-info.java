module org.una.tramites.fxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.una.tramites.fxapp to javafx.fxml;
    exports org.una.tramites.fxapp;
    requires lombok;
}
