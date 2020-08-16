package com.bignerdranch.android.goodslist.database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.bignerdranch.android.goodslist.pojo.Goods;


public class GoodsDb extends Application {
    private static SharedPreferences mSharedPreferences;

    public void saveData(Goods goods ,Context context, String str) {
        mSharedPreferences = context.getSharedPreferences("GoodsDb" ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(String.valueOf(goods.getId()), str).apply();


    }

    public String  loadData(Goods goods, Context context) {
        mSharedPreferences = context.getSharedPreferences("GoodsDb" ,Context.MODE_PRIVATE);
        return mSharedPreferences.getString(String.valueOf(goods.getId()), "-1");
    }



}
