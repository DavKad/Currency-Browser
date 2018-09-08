package com.practice.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BrowseWindowController {
    @FXML
    Button buttonBack;
    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.close();
    }
    //TODO Assing to all buttons back function
    @FXML
    public void backToTheMainWindow(ActionEvent actionEvent) {


    }
}
