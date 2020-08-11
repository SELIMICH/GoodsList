package com.bignerdranch.android.goodslist.api;

import com.bignerdranch.android.goodslist.pojo.Goods;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products?category=93&key=47be9031474183ea92958d5e255d888e47bdad44afd5d7b7201d0eb572be5278")
    Call<List<Goods>> getGoods();
}
