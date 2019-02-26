package com.noemi.android.currency.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = FavoriteEntity.class, version = 1, exportSchema = false)
public abstract class FavoriteDataBase  extends RoomDatabase{

    private static final String FAVORITE = "favorite_db";
    private static final Object LOCK = new Object();
    private static FavoriteDataBase sIntance;

    public static FavoriteDataBase getFavoriteDataBase(Context context){
        if (sIntance == null){
            synchronized (LOCK){
                sIntance = Room.databaseBuilder(context, FavoriteDataBase.class,
                        FavoriteDataBase.FAVORITE).build();
            }
        }
       return sIntance;
    }

    public abstract FavoriteDAO getDao();
}
