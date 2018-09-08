package com.practice;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

import static com.practice.GetCurrencyProperties.PKOCurrencyProperties;
import static com.practice.GetCurrencyProperties.STDCurrencyProperties;

public class CurrencyComparison {

    public static void findCurrencyByNameInPKO(ArrayList<ArrayList<GetCurrencyProperties>> banks, @NotNull String word) {
        for (ArrayList<GetCurrencyProperties> x : banks) {
            if (x.equals(PKOCurrencyProperties))
                for (GetCurrencyProperties y : x) {
                    if (y.getTitle().equals(word)) {

                    }
                }
        }
    }

    public static void findCurrencyByNameInSTD(ArrayList<ArrayList<GetCurrencyProperties>> banks, @NotNull String word) {
        for (ArrayList<GetCurrencyProperties> x : banks) {
            if (x.equals(STDCurrencyProperties))
                for (GetCurrencyProperties y : x) {
                    if (y.getTitle().equals(word)) {
                    }
                }
        }
    }

}