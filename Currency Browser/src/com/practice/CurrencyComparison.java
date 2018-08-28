package com.practice;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

import static com.practice.GetCurrencyProperties.PKOCurrencyProperties;
import static com.practice.GetCurrencyProperties.BZWBKCurrencyProperties;

public class CurrencyComparison implements Comparable<GetCurrencyProperties> {

    public static void findCurrencyByNameInPKO(ArrayList<ArrayList<GetCurrencyProperties>> banks, @NotNull String word) {
        for (ArrayList<GetCurrencyProperties> x : banks) {
            if (x.equals(PKOCurrencyProperties))
                for (GetCurrencyProperties y : x) {
                    if (y.getTitle().equals(word)) {
                        System.out.println(y.getPurchaseValue());
                        System.out.println(y.getSaleValue());
                    }
                }
        }
    }

    public static void findCurrencyByNameInBZWBK(ArrayList<ArrayList<GetCurrencyProperties>> banks, @NotNull String word) {
        for (ArrayList<GetCurrencyProperties> x : banks) {
            if (x.equals(BZWBKCurrencyProperties))
                for (GetCurrencyProperties y : x) {
                    if (y.getTitle().equals(word)) {
                        System.out.println(y.getPurchaseValue());
                        System.out.println(y.getSaleValue());
                    }
                }
        }
    }

    /*TODO Currency Values Comparison Method*/
    @Override
    public int compareTo(GetCurrencyProperties getCurrencyProperties) {
        return 0;
    }
}