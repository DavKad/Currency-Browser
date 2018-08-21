package com.practice;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Currency {
    private String title;
    private Double saleValue;
    private Double purchaseValue;
    private static ArrayList<Currency> PKOCurrencyValues = new ArrayList<>();
    private static ArrayList<Currency> BZWBKCurrencyValues = new ArrayList<>();

    public Currency(String title, Double saleValue, Double purchaseValue) {
        this.title = title;
        this.saleValue = saleValue;
        this.purchaseValue = purchaseValue;
    }

    @Getter
    public String getTitle() {
        return title;
    }

    @Getter
    public Double getSaleValue() {
        return saleValue;
    }

    @Getter
    public Double getPurchaseValue() {
        return purchaseValue;
    }


    public static ArrayList<Currency> getPKOCurrency() throws Exception {

        /*Declare structures*/
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Double> purchaseValue = new ArrayList<>();
        ArrayList<Double> saleValue = new ArrayList<>();
        String title, value;
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
                for (int i = 2; i < getTd.size() - 1; i++) {
                    if (i == 2 || i == 6) {
                        Element column = getTd.get(i);
                        value = column.select("td").text();
                        if (i == 2) {
                            if (!(value.contains("nd."))) {
                                purchaseValue.add(Double.parseDouble(value));
                            } else {
                                purchaseValue.add(Double.NaN);
                            }
                        }
                        if (i == 6) {
                            if (!(value.contains("nd."))) {
                                saleValue.add(Double.parseDouble(value));
                            } else {
                                saleValue.add(Double.NaN);
                            }
                        }
                    }
                }
            } else {
                break;
            }
            valuesCounter++;
        }

        /*Fill list of Currency objects*/
        for (int i = 0; i < titles.size(); i++) {
            PKOCurrencyValues.add(new Currency(titles.get(i), saleValue.get(i), purchaseValue.get(i)));
        }
        return PKOCurrencyValues;
    }

    public static ArrayList<Currency> getBZWBKCurrency() throws Exception {

        /*Declare structures*/
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<Double> saleValues = new ArrayList<>();
        ArrayList<Double> purchaseValues = new ArrayList<>();
        String title, value;
        int dataCounter = 0;

        /*Getting data from the HTML file*/
        final Document document = Jsoup.connect("https://www.bzwbk.pl/przydatne-informacje/kursy-walut/dewizy/kursy-walut-dewizy.html").get();

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
            BZWBKCurrencyValues.add(new Currency(titles.get(i), saleValues.get(i), purchaseValues.get(i)));
        }
        return BZWBKCurrencyValues;
    }
}