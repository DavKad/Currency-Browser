package com.practice.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public Button browseButton;
    public Button buttonFind;
    public Button buttonConverter;
    public StackPane toSwap;

    @FXML
    void initialize(){

        browseButton.setOnAction(s ->{
            try {
                StackPane stack = FXMLLoader.load(getClass().getResource("resources/BrowseWindow.fxml"));
                Scene scene = new Scene(stack);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Currency Browser");
                stage.getIcons().add(new Image("com/practice/controllers/resources/logo.png"));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonFind.setOnAction(s -> {
            try {
                StackPane stack = FXMLLoader.load(getClass().getResource("resources/FindWindow.fxml"));
                toSwap.getChildren().setAll(stack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonConverter.setOnAction(s -> {
            try{
                StackPane stack = FXMLLoader.load(getClass().getResource("resources/ConvertWindow.fxml"));
                toSwap.getChildren().setAll(stack);
            } catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}
