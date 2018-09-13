package com.practice;

import jdk.nashorn.internal.objects.annotations.Setter;

public class CurrencyConverter {

    private Double exchangeRate;

    @Setter
    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Double convertCurrencyToPLN(Double value) {
        return value * exchangeRate;
    }

    public Double convertCurrencyFromPLN(Double value) {
        return value / exchangeRate;
    }
}
