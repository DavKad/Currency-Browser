package com.practice;

public class Main {

    public static void main(String[] args) {
        try {
            Currency.getPKOCurrency();
            Currency.getBZWBKCurrency();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
