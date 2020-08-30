module org.una.tramites.tramitesapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.una.tramites.tramitesapp to javafx.fxml;
    exports org.una.tramites.tramitesapp;
}
