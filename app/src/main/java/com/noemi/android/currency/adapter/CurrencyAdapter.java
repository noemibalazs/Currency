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
import com.noemi.android.currency.model.CurrencyItem;
import com.noemi.android.currency.room.CurrencyEntity;
import com.noemi.android.currency.room.CurrencyExecutor;

import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<CurrencyEntity> {

    public CurrencyAdapter(@NonNull Context context, List<CurrencyEntity> itemList) {
        super(context, 0, itemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.currency_item, parent, false);
        }

        CurrencyEntity currencyItem = getItem(position);
        String name = currencyItem.getName();
        String symbol = currencyItem.getSymbol();
        double basic = currencyItem.getRate();
        double amount = currencyItem.getAmount();

        String basicRate = String.format("%.2f", basic);
        String amountRate = String.format("%.2f", amount);

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
