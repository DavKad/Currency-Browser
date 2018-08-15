package com.practice;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {

            /*List which contains Lists with Currency instances and theirs properites*/
            ArrayList<ArrayList<Currency>> banks = new ArrayList<>();
            banks.add(Currency.getPKOCurrency());
            banks.add(Currency.getBZWBKCurrency());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
