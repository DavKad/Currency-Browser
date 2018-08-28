package com.practice;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class CurrencyComparison {
    /*TODO One specific bank, not all*/
    public static void findByName(ArrayList<ArrayList<GetCurrencyProperties>> list, @NotNull String word) {
        Iterator<ArrayList<GetCurrencyProperties>> listIterator = list.iterator();
        while (listIterator.hasNext()) {
            ArrayList<GetCurrencyProperties> x = listIterator.next();
            Iterator<GetCurrencyProperties> y = x.iterator();
            while (y.hasNext()) {
                GetCurrencyProperties value = y.next();
                if (value.getTitle().equals(word)) {
                    System.out.println(value.getPurchaseValue());
                    System.out.println(value.getSaleValue());
                }
            }
        }
    }

}