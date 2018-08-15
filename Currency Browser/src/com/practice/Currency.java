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
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        String title, value, temp;
        int tableCounter = 0;

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
            if (tableCounter < 22) {
                Elements getTd = data.select("td");
                for (int i = 1; i < getTd.size(); i++) {
                    Element column = getTd.get(i);
                    temp = column.select("td").text();
                    if (!(temp.contains("%") || temp.contains("S"))) {
                        value = temp;
                        values.add(value);
                    }
                }
            } else {
                break;
            }
            tableCounter++;
        }
        return PKOCurrencyValues;
    }

    public static ArrayList<Currency> getBZWBKCurrency() throws Exception {

        /*Declare structures*/
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        String title, value  , temp;
        int dataCounter = 0;
        /*Getting data from the HTML file*/
        final Document document = Jsoup.connect("https://www.bzwbk.pl/przydatne-informacje/kursy-walut/dewizy/kursy-walut-dewizy.html").get();

        /*Getting currency titles and values*/
        for (Element data : document.select("div.kw_date, table.kw_table tbody > tr")) {
            if (dataCounter < 18) {
                title = data.select("td:lt(2)").text();
                Elements row = data.select("td");
                for(int i = 2; i < row.size() - 2; i++) {
                    Element getTD = row.get(i);
                    temp = getTD.select("td").text();
                    value = temp;
                    values.add(value);
                }
                titles.add(title);
                dataCounter++;
            } else {
                break;
            }
        }
        return BZWBKCurrencyValues;
    }
}