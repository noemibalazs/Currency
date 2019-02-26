package com.noemi.android.currency.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.noemi.android.currency.R;
import com.noemi.android.currency.adapter.CurrencyAdapter;
import com.noemi.android.currency.adapter.FavoriteAdapter;
import com.noemi.android.currency.room.CurrencyDataBase;
import com.noemi.android.currency.room.CurrencyEntity;
import com.noemi.android.currency.room.CurrencyExecutor;
import com.noemi.android.currency.room.CurrencyViewModel;
import com.noemi.android.currency.room.FavoriteDataBase;
import com.noemi.android.currency.room.FavoriteEntity;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private ListView listView;
    private FavoriteDataBase dataBase;
    private FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBase = FavoriteDataBase.getFavoriteDataBase(this);

        listView = findViewById(R.id.add_list_view);

        CurrencyViewModel viewModel = ViewModelProviders.of(this).get(CurrencyViewModel.class);
        viewModel.getListLiveData().observe(this, new Observer<List<CurrencyEntity>>() {
            @Override
            public void onChanged(@Nullable List<CurrencyEntity> currencyEntities) {

                List<FavoriteEntity> listEntity = new ArrayList<>();

                for (int i = 0; i< currencyEntities.size(); i++){

                    CurrencyEntity entity = currencyEntities.get(i);
                    listEntity.add(new FavoriteEntity(entity.getName(), entity.getSymbol(), entity.getRate(), entity.getRate()));

                }

                favoriteAdapter = new FavoriteAdapter(getApplicationContext(), listEntity);
                listView.setAdapter(favoriteAdapter);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                CurrencyExecutor.getExecutor().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        FavoriteEntity entity = (FavoriteEntity) listView.getItemAtPosition(position);
                        dataBase.getDao().insertFavorite(entity);

                    }
                });

                Toast.makeText(AddActivity.this, getString(R.string.favorite), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
