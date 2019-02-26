package com.noemi.android.currency.model;

import com.google.gson.annotations.SerializedName;

public class Rates {

    @SerializedName("BGN")
    private double bGN;

    @SerializedName("CHF")
    private double cHF;

    @SerializedName("CZK")
    private double cZK;

    @SerializedName("DKK")
    private double dKK;

    @SerializedName("EUR")
    private double eUR;

    @SerializedName("GBP")
    private double gBP;

    @SerializedName("HRK")
    private double hRK;

    @SerializedName("HUF")
    private double hUF;

    @SerializedName("NOK")
    private double nOK;

    @SerializedName("PLN")
    private double pLN;

    @SerializedName("RON")
    private double rON;

    @SerializedName("RUB")
    private double rUB;

    @SerializedName("SEK")
    private double sEK;

    @SerializedName("USD")
    private double uSD;


    public double getbGN() {
        return bGN;
    }
    public double getcHF() {
        return cHF;
    }

    public double geteUR() {
        return eUR;
    }

    public double getcZK() {
        return cZK;
    }

    public double getdKK() {
        return dKK;
    }

    public double getgBP() {
        return gBP;
    }

    public double gethRK() {
        return hRK;
    }

    public double gethUF() {
        return hUF;
    }

    public double getnOK() {
        return nOK;
    }

    public double getrON() {
        return rON;
    }

    public double getrUB() {
        return rUB;
    }

    public double getsEK() {
        return sEK;
    }


    public double getuSD() {
        return uSD;
    }

    public double getpLN() {
        return pLN;
    }
}

