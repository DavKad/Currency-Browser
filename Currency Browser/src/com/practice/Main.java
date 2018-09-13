package com.practice;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    public static HashMap<String, ArrayList<CurrencyProperties>> banks = new HashMap<>();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /*Loading data from Banks Websites*/
        ArrayList<CurrencyProperties> PKOCurrency = CurrencyProperties.getPKOCurrency();
        ArrayList<CurrencyProperties> STDCurrency = CurrencyProperties.getSTDCurrency();
        banks.put("PKO Bank Polski S.A.", PKOCurrency);
        banks.put("Santander Bank Polski S.A.", STDCurrency);

        /*Main Window GUI*/
        StackPane stackPane = FXMLLoader.load(getClass().getResource("controllers/resources/MainWindow.fxml"));
        Scene scene = new Scene(stackPane);
        primaryStage.getIcons().add(new Image("com/practice/controllers/resources/logo.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Currency Browser");
        primaryStage.show();
        primaryStage.setOnCloseRequest(s -> Platform.exit());
    }
}
