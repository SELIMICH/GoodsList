package com.bignerdranch.android.goodslist.screens.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.goodslist.R;
import com.bignerdranch.android.goodslist.adapters.GoodsAdapter;
import com.bignerdranch.android.goodslist.api.ApiService;
import com.bignerdranch.android.goodslist.pojo.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoodsListActivity extends AppCompatActivity{

    private RecyclerView mRecyclerViewGoods;
    private ArrayList<Goods> data;
    private GoodsAdapter mAdapter;
    private String  TAG = "GoodsListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        initViews();
    }

    private void initViews() {
        mRecyclerViewGoods = (RecyclerView) findViewById(R.id.recyclerViewGoods);
        mRecyclerViewGoods.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(getApplicationContext());
        mRecyclerViewGoods.setLayoutManager(layoutManager);
        data = new ArrayList<>();
        loadJSON();

    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://peretz-group.ru/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Goods>> call = apiService.getGoods();
        call.enqueue(new Callback<List<Goods>>() {
            @Override
            public void onResponse(Call<List<Goods>> call, Response<List<Goods>> response) {
                List<Goods> goodsResponse = response.body();
                Log.i(TAG, "onResponse: " + response.body());
                assert goodsResponse != null;
                data.addAll(goodsResponse);
                mAdapter = new GoodsAdapter(data);
                mRecyclerViewGoods.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Goods>> call, Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_goods_list_search,menu);
        return super.onCreateOptionsMenu(menu);
    }


}