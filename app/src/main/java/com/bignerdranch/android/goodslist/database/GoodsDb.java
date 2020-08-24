package com.bignerdranch.android.goodslist.database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.bignerdranch.android.goodslist.api.ApiService;
import com.bignerdranch.android.goodslist.pojo.Goods;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GoodsDb extends Application {
    private SharedPreferences mSharedPreferences;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://peretz-group.ru/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiService apiService = retrofit.create(ApiService.class);

    public ApiService getApiService() {
        return apiService;
    }

    public void saveData(Goods goods ,Context context, String str) {
        mSharedPreferences = context.getSharedPreferences("GoodsDb" ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(String.valueOf(goods.getId()), str).apply();


    }

    public String  loadData(Goods goods, Context context) {
        mSharedPreferences = context.getSharedPreferences("GoodsDb" ,Context.MODE_PRIVATE);
        return mSharedPreferences.getString(String.valueOf(goods.getId()), "0");
    }

}
