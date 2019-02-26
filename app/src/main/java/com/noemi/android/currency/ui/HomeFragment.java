package com.noemi.android.currency.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.noemi.android.currency.R;
import com.noemi.android.currency.adapter.CurrencyAdapter;
import com.noemi.android.currency.model.Currency;
import com.noemi.android.currency.model.CurrencyItem;
import com.noemi.android.currency.network.ApiClient;
import com.noemi.android.currency.network.ApiInterface;
import com.noemi.android.currency.room.CurrencyDataBase;
import com.noemi.android.currency.room.CurrencyEntity;
import com.noemi.android.currency.room.CurrencyExecutor;
import com.noemi.android.currency.room.CurrencyViewModel;

import java.lang.ref.PhantomReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment{

    public HomeFragment() { }

    private EditText editText;
    private ListView listView;
    private CurrencyAdapter adapter;
    private double amount = 1;

    private CurrencyDataBase dataBase;
    private boolean check = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

        getActivity().setTitle(getString(R.string.title_home));

        if (check){
            Toast.makeText(getContext(), getString(R.string.attention1), Toast.LENGTH_SHORT).show();
            check = false;
        }

        dataBase = CurrencyDataBase.getDatabase(getContext());

        listView = view.findViewById(R.id.list_view);
        editText = view.findViewById(R.id.et_amount);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.toString().trim().length() > 0){
                    String text = editText.getText().toString().trim();
                    amount = Double.parseDouble(text);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        CurrencyViewModel viewModel = ViewModelProviders.of(this).get(CurrencyViewModel.class);
        viewModel.getListLiveData().observe(this, new Observer<List<CurrencyEntity>>() {
            @Override
            public void onChanged(@Nullable List<CurrencyEntity> currencyEntities) {
                adapter = new CurrencyAdapter(getContext(), currencyEntities);
                listView.setAdapter(adapter);

            }
        });

        return view;
    }


    public void setupUi(){

        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Currency> currencyCall = apiInterface.getCurrency(getString(R.string.api_key));
        currencyCall.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), getString(R.string.toast), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body()!=null){

                    Currency currency = response.body();
                    List<CurrencyItem> currencyItemList = currency.getCurrencyItem(amount);

                    for(int i = 0; i< currencyItemList.size(); i++){

                        CurrencyItem item = currencyItemList.get(i);
                        final CurrencyEntity entity = new CurrencyEntity(item.getCurrencyName(),
                                item.getCurrencySymbol(), item.getBasicRate(), item.getCalcRate());

                        CurrencyExecutor.getExecutor().getDiskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                dataBase.currencyDAO().insertCurrency(entity);

                            }
                        });
                    }
                }

            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ok){
            setupUi();
            return true;
        }
        return false;
    }
}
