package com.noemi.android.currency.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import java.util.List;


@Dao
public interface CurrencyDAO {

    @Query("SELECT * FROM currency")
    LiveData<List<CurrencyEntity>> getListEntity();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrency(CurrencyEntity entity);


}
