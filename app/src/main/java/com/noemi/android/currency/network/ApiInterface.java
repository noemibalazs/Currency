package com.noemi.android.currency.network;

import com.noemi.android.currency.model.Currency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("latest")
    Call<Currency> getCurrency(@Query("access_key") String key);
}
