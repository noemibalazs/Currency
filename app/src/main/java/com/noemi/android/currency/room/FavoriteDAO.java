package com.noemi.android.currency.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoriteDAO {


    @Query("SELECT * FROM favorite")
    LiveData<List<FavoriteEntity>> getFavoriteList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(FavoriteEntity favoriteEntity);

    @Delete
    void deleteCurrency(FavoriteEntity entity);

}
