package com.practice.controllers;

import com.practice.CurrencyConverter;
import com.practice.CurrencyProperties;
import com.practice.interfaces.Selection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.ArrayList;

import static com.practice.Main.banks;

public class ConvertWindow implements Selection {

    private String banksSelection;
    private ToggleGroup toggleRadioButton = new ToggleGroup();
    private Notifications notification;

    @FXML
    public StackPane backToMain;
    public Button convertButton;
    public Button backButton;
    public RadioButton onPLN;
    public RadioButton fromPLN;
    public TextField moneyValue;
    public Text resultInfo;
    public Text result;
    public ComboBox<String> banksCombo;
    public ComboBox<String> currencyCombo;

    @Override
    public void makeWarningNotification(String message) {
        notification = Notifications.create()
                .title("Warning!")
                .text(message)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
    }

    @Override
    public void specifiedButtonAction() {
        if (banksCombo.getItems().isEmpty() || currencyCombo.getSelectionModel().isEmpty()) {
            makeWarningNotification("You didn't choose bank or currency");
            notification.showWarning();
        } else {
            if (moneyValue.getText().trim().isEmpty()) {
                ;
                makeWarningNotification("You didn't set money value");
                notification.showWarning();
            } else {
                if (toggleRadioButton.getSelectedToggle() != null) {
                    if (banks.containsKey(banksSelection)) {
                        String currencySelection = currencyCombo.getSelectionModel().getSelectedItem();
                        ArrayList<CurrencyProperties> holdCurrencies = banks.get(banksSelection);
                        Double purchaseValue = 0.0, saleValue = 0.0;
                        for (CurrencyProperties x : holdCurrencies) {
                            if (x.getTitle().equals(currencySelection)) {
                                purchaseValue = x.getPurchaseValue();
                                saleValue = x.getSaleValue();
                            }
                        }
                        CurrencyConverter converter = new CurrencyConverter();
                        double exchangeValue;
                        if (((RadioButton) toggleRadioButton.getSelectedToggle()).getText().equals("on PLN")) {
                            converter.setExchangeRate(saleValue);
                            if (moneyValue.getText().contains(",")) {
                                exchangeValue = Double.parseDouble(moneyValue.getText().replace(',', '.'));
                            } else {
                                exchangeValue = Double.parseDouble(moneyValue.getText());
                            }
                            result.setText(Double.toString(converter.convertCurrencyToPLN(exchangeValue)));
                        } else {
                            converter.setExchangeRate(purchaseValue);
                            if (moneyValue.getText().contains(",")) {
                                exchangeValue = Double.parseDouble(moneyValue.getText().replace(',', '.'));
                            } else {
                                exchangeValue = Double.parseDouble(moneyValue.getText());
                            }
                            result.setText(Double.toString(converter.convertCurrencyFromPLN(exchangeValue)));
                        }
                        resultInfo.setVisible(true);
                        result.setVisible(true);
                    }
                } else {
                    makeWarningNotification("You didn't choose conversion option");
                    notification.showWarning();
                }
            }
        }
    }

    @Override
    public void fillComboBoxAction() {
        currencyCombo.getItems().clear();
        banksSelection = banksCombo.getSelectionModel().getSelectedItem();
        if (banks.containsKey(banksSelection)) {
            ArrayList<CurrencyProperties> temp = banks.get(banksSelection);
            for (CurrencyProperties x : temp) {
                currencyCombo.getItems().add(x.getTitle());
            }
        }
    }

    @FXML
    void initialize() {
        /*ChoiceBox*/
        banksCombo.getItems().add("PKO Bank Polski S.A.");
        banksCombo.getItems().add("Santander Bank Polski S.A.");
        banksCombo.getItems().add("ING Bank Śląski");
        banksCombo.getItems().add("mBank");


        onPLN.setToggleGroup(toggleRadioButton);
        fromPLN.setToggleGroup(toggleRadioButton);

        backButton.setOnAction(s -> {
            try {
                StackPane stack = FXMLLoader.load(getClass().getResource("resources/MainWindow.fxml"));
                backToMain.getChildren().setAll(stack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        banksCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            fillComboBoxAction();
        });

        convertButton.setOnAction(s -> {
            specifiedButtonAction();
        });
    }
}
