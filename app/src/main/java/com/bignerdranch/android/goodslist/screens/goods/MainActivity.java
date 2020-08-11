package com.bignerdranch.android.goodslist.screens.goods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bignerdranch.android.goodslist.R;

public class MainActivity extends AppCompatActivity {
    private Button mBtnGoToGoodsListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnGoToGoodsListActivity = (Button) findViewById(R.id.btn_go_to_goods_list_activity);
        mBtnGoToGoodsListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoodsListActivity.class);
                startActivity(intent);
            }
        });
    }
}