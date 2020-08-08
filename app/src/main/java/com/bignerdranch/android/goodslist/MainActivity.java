package com.bignerdranch.android.goodslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.bignerdranch.android.goodslist.adapters.GoodsAdapter;
import com.bignerdranch.android.goodslist.api.ApiFactory;
import com.bignerdranch.android.goodslist.api.ApiService;
import com.bignerdranch.android.goodslist.pojo.Goods;
import com.bignerdranch.android.goodslist.pojo.GoodsResponse;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewGoods;
    private GoodsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerViewGoods = findViewById(R.id.recyclerViewGoods);
        mAdapter = new GoodsAdapter();
        mAdapter.setGoods(new ArrayList<Goods>());
        mRecyclerViewGoods.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewGoods.setAdapter(mAdapter);
        ApiFactory apiFactory = ApiFactory.getApiFactory();
        ApiService apiService = apiFactory.getApiService();
        apiService.getGoods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GoodsResponse>() {
                    @Override
                    public void accept(GoodsResponse goodsResponce) throws Exception {
                        mAdapter.setGoods(goodsResponce.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}