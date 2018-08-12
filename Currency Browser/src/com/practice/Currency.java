package com.practice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Currency {
    private String title;
    private Double saleValue;
    private Double purchaseValue;
    private Double spreadValue;
    private Double mediumValue;


    public Currency(String title, Double saleValue, Double purchaseValue) {
        this.title = title;
        this.saleValue = saleValue;
        this.purchaseValue = purchaseValue;
    }

    public Currency(String title, Double saleValue, Double purchaseValue, Double spreadValue, Double mediumValue) {
        this.title = title;
        this.saleValue = saleValue;
        this.purchaseValue = purchaseValue;
        this.spreadValue = spreadValue;
        this.mediumValue = mediumValue;
    }

    public String getTitle() {
        return title;
    }

    public Double getSaleValue() {
        return saleValue;
    }

    public Double getPurchaseValue() {
        return purchaseValue;
    }

    public Double getSpreadValue() {
        return spreadValue;
    }

    public Double getMediumValue() {
        return mediumValue;
    }

    public static ArrayList<Currency> getPKOCurrency() throws Exception {

        /*Declare structures*/
        ArrayList<Currency> PKOCurrencyValues = new ArrayList<>();
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

        ArrayList<Currency> BZWBKCurrencyValues = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        String title, value;
        int dataCounter = 0;
        /*Getting data from the HTML file*/
        final Document document = Jsoup.connect("https://www.bzwbk.pl/przydatne-informacje/kursy-walut/dewizy/kursy-walut-dewizy.html").get();

        for (Element data : document.select("div.kw_date, table.kw_table tbody > tr")) {

            if(dataCounter < 18) {
                title = data.select("td:lt(2)").text();
                value = data.select("td:gt(1)").text();

                titles.add(title);
                values.add(value);
                System.out.println(title);
                System.out.println(value);
                dataCounter++;
            }else{
                break;
            }
        }
        return BZWBKCurrencyValues;
    }
}