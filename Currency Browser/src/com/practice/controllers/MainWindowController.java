package com.practice.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController  {

    @FXML
    public Button browseButton;

    @FXML
    public Button buttonFind;

    @FXML
    public Button buttonCompare;

    @FXML
    public Button buttonExit;

    @FXML
    void initialize(){
        browseButton.setOnAction( s ->{
            try {
                StackPane stack = FXMLLoader.load(getClass().getResource("resources/BrowseWindow.fxml"));
                Scene scene = new Scene(stack);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Currency Browser");
                stage.getIcons().add(new Image("com/practice/logo.png"));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //TODO Closing a main window
        buttonExit.setOnAction(s -> {

        });
    }
}
