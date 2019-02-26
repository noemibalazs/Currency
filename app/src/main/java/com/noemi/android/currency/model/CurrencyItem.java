package com.noemi.android.currency.model;

public class CurrencyItem {

    private String currencyName;
    private String symbol;
    private Double basicRate;
    private Double calcRate;

    public CurrencyItem(String name, String symbol, Double basicRate, Double calcRate) {
        this.currencyName = name;
        this.symbol = symbol;
        this.basicRate = basicRate;
        this.calcRate = calcRate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencySymbol() {
        return symbol;
    }

    public Double getBasicRate() {
        return basicRate;
    }

    public Double getCalcRate() {
        return calcRate;
    }

}
