package com.practice.controllers;

import com.practice.CurrencyProperties;
import com.practice.interfaces.Selection;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.ArrayList;
import static com.practice.Main.banks;

public class FindController implements Selection {

    private String banksSelection;
    private Double purchase;
    private Double sale;
    private Notifications notification;

    @FXML
    public Button backButton;
    public Button searchButton;
    public Text badSelection;
    public StackPane backToMain;
    public ComboBox<String> bankComboBox;
    public ComboBox<String> currencyComboBox;

    @FXML
    public TableView<CurrencyProperties> currencyTable;
    public TableColumn<CurrencyProperties, Double> purchaseValue;
    public TableColumn<CurrencyProperties, Double> saleValue;

    @Override
    public void fillComboBoxAction() {
        currencyComboBox.getItems().clear();
        banksSelection = bankComboBox.getSelectionModel().getSelectedItem();
        if (banks.containsKey(banksSelection)) {
            ArrayList<CurrencyProperties> holdCurrencies =banks.get(banksSelection);
            for(CurrencyProperties x : holdCurrencies){
                currencyComboBox.getItems().add(x.getTitle());
            }
        }
    }
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
        if(bankComboBox.getItems().isEmpty() | currencyComboBox.getSelectionModel().isEmpty()){
            makeWarningNotification("You didn't choose bank or currency");
            notification.showWarning();
            currencyTable.setVisible(false);
        }else{
            badSelection.setVisible(false);
            String currencySelection = currencyComboBox.getSelectionModel().getSelectedItem();

            ArrayList<CurrencyProperties> holdCurrencies = banks.get(banksSelection);
            for(CurrencyProperties x : holdCurrencies){
                if(x.getTitle().equals(currencySelection)){
                    purchase = x.getPurchaseValue();
                    sale = x.getSaleValue();
                }
            }
            ObservableList<CurrencyProperties> currencyList = FXCollections.observableArrayList(
                    new CurrencyProperties(currencySelection, sale, purchase)
            );
            saleValue.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue().getSaleValue()));
            purchaseValue.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue().getPurchaseValue()));
            currencyTable.setItems(currencyList);
            currencyTable.setVisible(true);
        }
    }

    @FXML
    void initialize() {
        /*Bank ComboBox*/
        bankComboBox.getItems().add("PKO Bank Polski S.A.");
        bankComboBox.getItems().add("Santander Bank Polski S.A.");
        bankComboBox.getItems().add("ING Bank Śląski");
        bankComboBox.getItems().add("mBank");

        /*Fill currency ComboBox*/
        bankComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            fillComboBoxAction();
        });
        /*Searching currency values*/
        searchButton.setOnAction(s ->{
            specifiedButtonAction();
        });
        /*Back button to main window*/
        backButton.setOnAction(s -> {
            try {
                StackPane stack = FXMLLoader.load(getClass().getResource("resources/MainWindow.fxml"));
                backToMain.getChildren().setAll(stack);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
