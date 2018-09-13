package com.practice;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class CurrencyProperties {
    private String title;
    private Double saleValue;
    private Double purchaseValue;

    public CurrencyProperties(String title, Double saleValue, Double purchaseValue) {
        this.title = title;
        this.saleValue = saleValue;
        this.purchaseValue = purchaseValue;
    }

    @Getter
    public String getTitle() {
        return title;
    }

    public Double getSaleValue() {
        return saleValue;
    }

    public Double getPurchaseValue() {
        return purchaseValue;
    }

    static ArrayList<CurrencyProperties> getPKOCurrency() throws Exception {

        /*Declare structures*/
        ArrayList<CurrencyProperties> PKOCurrencyProperties = new ArrayList<>();

        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Double> purchaseValues = new ArrayList<>();
        ArrayList<Double> saleValues = new ArrayList<>();
        String title, saleValue, purchaseValue;
        int tableCounter = 0, valuesCounter = 0;

        /*Getting data from the HTML file*/
        final Document document = Jsoup.connect("https://www.pkobp.pl/waluty/").get();

        /*Getting currency titles*/
        for (Element data : document.select("div.course > h3")) {
            if (tableCounter < 22) {
                title = data.select(".course__title").text();
                titles.add(title);
            }
            tableCounter++;
        }

        /*Getting currency values*/
        for (Element data : document.select("div.course > table.course__table")) {
            if (valuesCounter < 22) {
                Elements getTd = data.select("td");
                Element purchaseColumn = getTd.get(2);
                purchaseValue = purchaseColumn.select("td").text();
                if (!(purchaseValue.contains("nd."))) {
                    purchaseValues.add(Double.parseDouble(purchaseValue));
                } else {
                    purchaseValues.add(Double.NaN);
                }
                Element saleColumn = getTd.get(6);
                saleValue = saleColumn.select("td").text();
                if (!(saleValue.contains("nd."))) {
                    saleValues.add(Double.parseDouble(saleValue));
                } else {
                    saleValues.add(Double.NaN);
                }
            } else {
                break;
            }
            valuesCounter++;
        }
        /*Fill list of Currency objects*/
        for (int i = 0; i < titles.size(); i++) {
            PKOCurrencyProperties.add(new CurrencyProperties(titles.get(i), saleValues.get(i), purchaseValues.get(i)));
        }
        return PKOCurrencyProperties;
    }

    static ArrayList<CurrencyProperties> getSTDCurrency() throws Exception {

        /*Declare structures*/
        ArrayList<CurrencyProperties> STDCurrencyProperties = new ArrayList<>();

        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Double> saleValues = new ArrayList<>();
        ArrayList<Double> purchaseValues = new ArrayList<>();
        String title, value;
        int dataCounter = 0;

        /*Getting data from the HTML file*/
        final Document document = Jsoup.connect("https://santander.pl/przydatne-informacje/kursy-walut/dewizy/kursy-walut-dewizy.html").get();

        /*Getting currency titles and values*/
        for (Element data : document.select("table.kw_table tbody > tr")) {
            if (dataCounter < 17) {
                title = data.select("td:lt(1)").text();
                Elements row = data.select("td");
                for (int i = 2; i < row.size() - 2; i++) {
                    Element getTD = row.get(i);
                    value = getTD.select("td").text();
                    if (i % 2 == 0)
                        purchaseValues.add(Double.parseDouble(value));
                    else
                        saleValues.add(Double.parseDouble(value));
                }
                titles.add(title);
                dataCounter++;
            } else {
                break;
            }
        }
        /*Fill list of Currency objects*/
        for (int i = 0; i < titles.size(); i++) {
            STDCurrencyProperties.add(new CurrencyProperties(titles.get(i), saleValues.get(i), purchaseValues.get(i)));
        }
        return STDCurrencyProperties;
    }
}