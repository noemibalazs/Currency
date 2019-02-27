package com.noemi.android.currency.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.noemi.android.currency.R;
import com.noemi.android.currency.room.FavoriteEntity;

import java.util.List;

public class FavoriteAdapter extends ArrayAdapter<FavoriteEntity> {

    public FavoriteAdapter(@NonNull Context context, List<FavoriteEntity> itemList) {
        super(context, 0, itemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.currency_item, parent, false);
        }

        FavoriteEntity currencyItem = getItem(position);
        String name = currencyItem.getName();
        String symbol = currencyItem.getSymbol();
        double basic = currencyItem.getRate();

        String basicRate = String.format("%.2f", basic);
        String amountRate = String.format("%.2f", basic * 1);

        TextView textName = view.findViewById(R.id.currency_name);
        TextView textSymbol = view.findViewById(R.id.currency_symbol);
        TextView textBasic = view.findViewById(R.id.currency_basic);
        TextView textAmount = view.findViewById(R.id.currency_amount);

        textName.setText(name);
        textSymbol.setText(symbol);
        textBasic.setText(basicRate);
        textAmount.setText(amountRate);

        return view;
    }


}

