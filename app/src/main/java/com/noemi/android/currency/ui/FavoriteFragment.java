package com.noemi.android.currency.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.noemi.android.currency.R;
import com.noemi.android.currency.activity.AddActivity;
import com.noemi.android.currency.adapter.RecycleAdapter;
import com.noemi.android.currency.room.CurrencyExecutor;
import com.noemi.android.currency.room.FavoriteDataBase;
import com.noemi.android.currency.room.FavoriteEntity;
import com.noemi.android.currency.room.FavoriteViewModel;

import java.util.List;

public class FavoriteFragment extends Fragment {

    public FavoriteFragment() { }

    private RecyclerView recyclerView;
    private RecycleAdapter adapter;

    private EditText editText;
    private TextView textAmount;
    private ImageView ok;

    private FloatingActionButton fb;
    private FavoriteDataBase dataBase;
    private double amount = 1;

    private boolean check = true;

    public static final String MY_PREF = "double";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        getActivity().setTitle(getString(R.string.title_favorite));
        setHasOptionsMenu(true);

        if (check){
            Toast.makeText(getContext(), getString(R.string.attention2), Toast.LENGTH_SHORT).show();
            check = false;
        }


        dataBase = FavoriteDataBase.getFavoriteDataBase(getContext());

        fb = rootView.findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = rootView.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        textAmount = rootView.findViewById(R.id.favorite_text);

        editText = rootView.findViewById(R.id.favorite_amount);

        ok = rootView.findViewById(R.id.ok_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                if (text.isEmpty()){
                    saveDouble(1);
                }else {
                    double value = Double.parseDouble(text);
                    saveDouble(value);
                    editText.setText("");
                }

                setUpUi();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence sequence, int start, int before, int count) {
                if (sequence.toString().trim().length() > 0) {
                    String text = editText.getText().toString().trim();
                    amount = Double.parseDouble(text);
                    String replace = "Amount: " + amount;
                    textAmount.setText(replace);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {

                CurrencyExecutor.getExecutor().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<FavoriteEntity> entityList = adapter.getEntityList();
                        FavoriteEntity entity = entityList.get(position);
                        dataBase.getDao().deleteCurrency(entity);
                    }
                });

            }
        }).attachToRecyclerView(recyclerView);

        return rootView;
    }

    public void setUpUi() {

        FavoriteViewModel viewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        viewModel.getFavoriteList().observe(getActivity(), new Observer<List<FavoriteEntity>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteEntity> currencyEntities) {

                SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

                if (preferences.contains(MY_PREF)) {

                    String prefValue = preferences.getString(MY_PREF, "");
                    amount = Double.parseDouble(prefValue);
                    adapter = new RecycleAdapter(getContext(), currencyEntities, amount);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }
        });
    }


    public void saveDouble(double amount) {

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MY_PREF, String.valueOf(amount));
        editor.apply();
    }


    @Override
    public void onResume() {
        super.onResume();
        setUpUi();
    }
}
