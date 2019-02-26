package com.noemi.android.currency.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Currency {

    @SerializedName("base")
    private String currency;

    @SerializedName("date")
    private String date;

   @SerializedName("rates")
    private Rates rates;

   public List<CurrencyItem> getCurrencyItem(double amount){
       List<CurrencyItem> items = new ArrayList<>();
       items.add(new CurrencyItem("Swiss Franc", "CHF", rates.getcHF(), amount * rates.getcHF()));
       items.add(new CurrencyItem("US Dollar", "USD", rates.getuSD(), amount * rates.getuSD()));
       items.add(new CurrencyItem("Euro", "EUR", rates.geteUR(), amount * rates.geteUR()));
       items.add(new CurrencyItem("British Pound", "GBP", rates.getgBP(), amount * rates.getgBP()));
       items.add(new CurrencyItem("Bulgarian Lev", "BGN", rates.getbGN(), amount * rates.getbGN()));
       items.add(new CurrencyItem("Czech Koruna", "CZK", rates.getcZK(), amount * rates.getcZK()));
       items.add(new CurrencyItem("Danish Krone", "DKK", rates.getdKK(), amount * rates.getdKK()));
       items.add(new CurrencyItem("Polish Zloty", "PLN", rates.getpLN(), amount * rates.getpLN()));
       items.add(new CurrencyItem("Croatian Kuna", "HRK", rates.gethRK(), amount * rates.gethRK()));
       items.add(new CurrencyItem("Hungarian Forint", "HUF", rates.gethUF(), amount * rates.gethUF()));
       items.add(new CurrencyItem("Norwegian Krone", "NOK", rates.getnOK(), amount * rates.getnOK()));
       items.add(new CurrencyItem("Romanian Lei", "RON", rates.getrON(), amount* rates.getrON()));
       items.add(new CurrencyItem("Russian Rubble", "RUB", rates.getrUB(), amount* rates.getrUB()));
       items.add(new CurrencyItem("Swedish Krona", "SEK", rates.getsEK(), amount* rates.getsEK()));

       return items;
   }
}
