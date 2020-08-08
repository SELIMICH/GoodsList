package com.bignerdranch.android.goodslist.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoodsResponse {
    @SerializedName("response")
    @Expose
    private List<Goods> response = null;

    public List<Goods> getResponse() {
        return response;
    }

    public void setResponse(List<Goods> response) {
        this.response = response;
    }
}
