package com.noemi.android.currency.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel{

    private LiveData<List<FavoriteEntity>> favoriteList;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);

        FavoriteDataBase db = FavoriteDataBase.getFavoriteDataBase(application);
        favoriteList = db.getDao().getFavoriteList();
    }

    public LiveData<List<FavoriteEntity>> getFavoriteList() {
        return favoriteList;
    }
}
