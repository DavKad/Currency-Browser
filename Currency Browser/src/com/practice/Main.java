package com.practice;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
    launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*List which contains Lists with GetCurrencyProperties instances*/
        ArrayList<ArrayList<GetCurrencyProperties>> banks = new ArrayList<>();
        banks.add(GetCurrencyProperties.getPKOCurrency());
//        banks.add(GetCurrencyProperties.getSTDCurrency());

        /*Main Window GUI*/
        StackPane stackPane = FXMLLoader.load(getClass().getResource("controllers/resources/MainWindow.fxml"));
        Scene scene = new Scene(stackPane);
        primaryStage.getIcons().add(new Image("com/practice/logo.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Currency Browser");
        primaryStage.show();
        primaryStage.setOnCloseRequest(s -> Platform.exit());
    }
}
