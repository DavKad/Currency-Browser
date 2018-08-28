package com.practice;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {

            /*List which contains Lists with GetCurrencyProperties instances*/
            ArrayList<ArrayList<GetCurrencyProperties>> banks = new ArrayList<>();
            banks.add(GetCurrencyProperties.getPKOCurrency());
            banks.add(GetCurrencyProperties.getBZWBKCurrency());

            /*CONCEPT*/
            String exampleWord = "Euro";
            CurrencyComparison.findCurrencyByNameInPKO(banks, exampleWord);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
