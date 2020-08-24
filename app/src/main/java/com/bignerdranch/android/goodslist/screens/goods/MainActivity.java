package com.bignerdranch.android.goodslist.screens.goods;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import com.bignerdranch.android.goodslist.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle((Html.fromHtml("<font color=\"#4C4C4C\">" + getString(R.string.app_name) + "</font>")));
        Button btnGoToGoodsListActivity = (Button) findViewById(R.id.btn_go_to_goods_list_activity);
        btnGoToGoodsListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoodsListActivity.class);
                startActivity(intent);
            }
        });
    }
}