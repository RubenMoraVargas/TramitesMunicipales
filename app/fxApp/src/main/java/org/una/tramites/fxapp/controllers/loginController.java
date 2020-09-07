package org.una.tramites.fxapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.una.tramites.fxapp.services.DataService;
import org.una.tramites.fxapp.services.SesionService;

public class LoginController {

    @FXML
    private TextField cedulaTextField;

    @FXML
    private TextField passwordTextField;

    private DataService dataService;
    private SesionService sesionService;
    
    @FXML
    public void login(ActionEvent event) {
        final String cedula = cedulaTextField.getText().trim();
        final String password = passwordTextField.getText().trim();
    }
}
