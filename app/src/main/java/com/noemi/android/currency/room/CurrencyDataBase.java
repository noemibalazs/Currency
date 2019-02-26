package com.noemi.android.currency.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = CurrencyEntity.class, version = 1, exportSchema = false)
public abstract class CurrencyDataBase extends RoomDatabase {

    private static final String DATABASE = "currency_db";
    private static final Object LOCK = new Object();
    private static CurrencyDataBase sInstance;

    public static CurrencyDataBase getDatabase(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context,
                        CurrencyDataBase.class,CurrencyDataBase.DATABASE).build();
            }
        }
        return sInstance;
    }

    public abstract CurrencyDAO currencyDAO();
}
