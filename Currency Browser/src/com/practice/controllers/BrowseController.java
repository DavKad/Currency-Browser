package com.practice.controllers;

import com.practice.CurrencyProperties;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.practice.Main.banks;

public class BrowseController {

    @FXML
    public Button buttonBack;
    public TableColumn<CurrencyProperties, String> PKOCurrencyName;
    public TableColumn<CurrencyProperties, Double> PKOSaleValue;
    public TableColumn<CurrencyProperties, Double> PKOPurchaseValue;
    public TableView<CurrencyProperties> PKOBrowseTable;
    public Tab PKOTab;
    public TableColumn<CurrencyProperties, String> STDCurrencyName;
    public TableColumn<CurrencyProperties, Double> STDSaleValue;
    public TableColumn<CurrencyProperties, Double> STDPurchaseValue;
    public TableView<CurrencyProperties> STDBrowseTable;
    public Tab STDTab;
    public TableColumn<CurrencyProperties, String> INGCurrencyName;
    public TableColumn<CurrencyProperties, Double> INGSaleValue;
    public TableColumn<CurrencyProperties, Double> INGPurchaseValue;
    public TableView<CurrencyProperties> INGBrowseTable;
    public Tab INGTab;
    public TableColumn<CurrencyProperties, String> MBKCurrencyName;
    public TableColumn<CurrencyProperties, Double> MBKSaleValue;
    public TableColumn<CurrencyProperties, Double> MBKPurchaseValue;
    public TableView<CurrencyProperties> MBKBrowseTable;
    public Tab MBKTab;


    private void fillTable(Tab bank, TableColumn<CurrencyProperties, String> name,
                   TableColumn<CurrencyProperties, Double> sale,
                   TableColumn<CurrencyProperties, Double> purchase,
                   TableView<CurrencyProperties> table) {
        ObservableList<CurrencyProperties> currencyList = FXCollections.observableArrayList();
        if (banks.containsKey(bank.getText())) {
            ArrayList<CurrencyProperties> temp = banks.get(bank.getText());
            for (CurrencyProperties x : temp) {
                String nameCurrency = x.getTitle();
                Double saleValue = x.getSaleValue();
                Double purchaseValue = x.getPurchaseValue();
                currencyList.add(new CurrencyProperties(nameCurrency, saleValue, purchaseValue));
            }
            name.setCellValueFactory(s -> new ReadOnlyStringWrapper(s.getValue().getTitle()));
            sale.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue().getSaleValue()));
            purchase.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(s.getValue().getPurchaseValue()));
            table.setItems(currencyList);
        }
    }

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        /*Fill PKO table*/
        fillTable(PKOTab, PKOCurrencyName, PKOSaleValue, PKOPurchaseValue, PKOBrowseTable);
        /*Fill STD table*/
        fillTable(STDTab, STDCurrencyName, STDSaleValue, STDPurchaseValue, STDBrowseTable);
        /*Fill ING table*/
        fillTable(INGTab,INGCurrencyName, INGSaleValue, INGPurchaseValue, INGBrowseTable);
        /*Fill mBank table*/
        fillTable(MBKTab, MBKCurrencyName, MBKSaleValue, MBKPurchaseValue, MBKBrowseTable);
    }
}
