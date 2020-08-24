package com.bignerdranch.android.goodslist.screens.goods;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;


import com.bignerdranch.android.goodslist.R;
import com.bignerdranch.android.goodslist.adapters.GoodsAdapter;
import com.bignerdranch.android.goodslist.database.GoodsDb;
import com.bignerdranch.android.goodslist.pojo.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class GoodsListActivity extends AppCompatActivity{

    private RecyclerView mRecyclerViewGoods;
    private ArrayList<Goods> data;
    private GoodsAdapter mAdapter;
    private String  TAG = "GoodsListActivity";
    private GoodsDb mGoodsDb = new GoodsDb();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle((Html.fromHtml("<font color=\"#4C4C4C\">" + getString(R.string.app_name) + "</font>")));
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

        Call<List<Goods>> call = mGoodsDb.getApiService().getGoods();
        call.enqueue(new Callback<List<Goods>>() {
            @Override
            public void onResponse(Call<List<Goods>> call, Response<List<Goods>> response) {
                List<Goods> goodsResponse = response.body();
                Log.i(TAG, "onResponse: " + response.body());
                assert goodsResponse != null;
                data.addAll(goodsResponse);
                mAdapter = new GoodsAdapter(data, GoodsListActivity.this);
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