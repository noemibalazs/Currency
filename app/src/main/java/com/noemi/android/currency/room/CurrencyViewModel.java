package com.noemi.android.currency.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class CurrencyViewModel extends AndroidViewModel {

    private LiveData<List<CurrencyEntity>> listLiveData;

    public CurrencyViewModel(@NonNull Application application) {
        super(application);

        CurrencyDataBase db = CurrencyDataBase.getDatabase(application);
        listLiveData = db.currencyDAO().getListEntity();
    }

    public LiveData<List<CurrencyEntity>> getListLiveData() {
        return listLiveData;
    }
}
