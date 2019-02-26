package com.noemi.android.currency.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favorite")
public class FavoriteEntity {

    @PrimaryKey
    @NonNull
    private String name;

    private String symbol;
    private double rate;
    private double amount;

    public FavoriteEntity(@NonNull String name, String symbol, double rate, double amount) {
        this.name = name;
        this.symbol = symbol;
        this.rate = rate;
        this.amount = amount;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getRate() {
        return rate;
    }

    public double getAmount() {
        return amount;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
