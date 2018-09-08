package com.practice.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class FindController {
    @FXML
    public Button buttonBack;
    public StackPane backToMain;

    @FXML
    void initialize(){
        buttonBack.setOnAction(s -> {
            try {
                StackPane stack = FXMLLoader.load(getClass().getResource("resources/MainWindow.fxml"));
                backToMain.getChildren().setAll(stack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
