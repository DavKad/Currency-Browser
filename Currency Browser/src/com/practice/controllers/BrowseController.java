package com.practice.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BrowseController {
    @FXML
    Button buttonBack;
    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.close();
    }
}
