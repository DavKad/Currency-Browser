package com.practice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        banks.add(GetCurrencyProperties.getBZWBKCurrency());

        /*Main Window GUI*/
        FXMLLoader mainWindow = new FXMLLoader();
        mainWindow.setLocation(this.getClass().getResource("resources/MainWindow.fxml"));
        StackPane stackPane = mainWindow.load();
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Currency Browser");
        primaryStage.show();
    }
}
