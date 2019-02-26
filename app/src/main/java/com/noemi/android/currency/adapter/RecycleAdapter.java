package com.noemi.android.currency.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noemi.android.currency.R;
import com.noemi.android.currency.room.CurrencyEntity;
import com.noemi.android.currency.room.FavoriteEntity;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.CurrencyViewHolder> {

    private Context context;
    private List<FavoriteEntity> entityList;
    private double amount;


    public RecycleAdapter(Context context, List<FavoriteEntity> entityList, double amount) {
        this.context = context;
        this.entityList = entityList;
        this.amount = amount;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {

        FavoriteEntity entity = entityList.get(position);
        double rate = entity.getRate();
        double calcAmount = amount * entity.getAmount();

        holder.name.setText(entity.getName());
        holder.symbol.setText(entity.getSymbol());
        holder.basic.setText(String.format("%.2f", rate));
        holder.amount.setText(String .format("%.2f", calcAmount));
    }

    @Override
    public int getItemCount() {
        return entityList == null ? 0: entityList.size();
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder{

        private TextView name, symbol, basic, amount;

        public CurrencyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.currency_name_item);
            symbol = itemView.findViewById(R.id.currency_symbol_item);
            basic = itemView.findViewById(R.id.currency_basic_item);
            amount = itemView.findViewById(R.id.currency_amount_item);
        }
    }


    public List<FavoriteEntity> getEntityList(){
        return entityList;
    }
}
