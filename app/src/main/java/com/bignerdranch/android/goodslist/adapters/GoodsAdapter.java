package com.bignerdranch.android.goodslist.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.goodslist.R;
import com.bignerdranch.android.goodslist.database.GoodsDb;
import com.bignerdranch.android.goodslist.pojo.Goods;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;



public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {
    private ArrayList<Goods> mGoods;
    private Context mContext;
    GoodsDb mGoodsDb = new GoodsDb();
    Goods goods;



    public GoodsAdapter(ArrayList<Goods> goods, Context context) {
        this.mGoods = goods;
        this.mContext = context;
        this.goods = goods.get(0);
    }

    @NonNull
    @Override
    public GoodsAdapter.GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent,false);
        return new GoodsViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    @Override
    public void onBindViewHolder(@NonNull final GoodsViewHolder holder, int position) {
         goods = mGoods.get(position);

    }

    @Override
    public int getItemCount() {
        return mGoods.size();
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mGoodsCount;
        private Button mButtonMinus;
        private Button mButtonPlus;


        @SuppressLint("SetTextI18n")
        public GoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView title = itemView.findViewById(R.id.goodsTitle);
            TextView description = itemView.findViewById(R.id.goodsDescription);
            TextView price = itemView.findViewById(R.id.goodsPrice);
            mImageView = itemView.findViewById(R.id.imageViewGoods);
            mGoodsCount = (TextView) itemView.findViewById(R.id.goodsCount);
            mButtonPlus = (Button) itemView.findViewById(R.id.btnPlus);
            mButtonMinus = (Button) itemView.findViewById(R.id.btnMinus);

            title.setText(goods.getName());
            description.setText(goods.getDescription());
            price.setText(goods.getPrice() + " ₽");

            Glide.with(mImageView.getContext())
                    .load(goods.getImage())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                            .centerCrop()
                            .error(R.drawable.ic_launcher_foreground))
                    .into(mImageView);

            bind(goods);





        }

        private void bind(final Goods goods) {
            String value = mGoodsDb.loadData(goods,mContext);
            mGoodsCount.setText(value);
            if (mGoodsCount.getText().equals("0")) {
                mButtonMinus.setVisibility(View.INVISIBLE);
                mGoodsCount.setVisibility(View.INVISIBLE);
            }

            mButtonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = String.valueOf(mGoodsCount.getText());
                    int counter = Integer.parseInt(str);
                    if (counter == 1) {
                        mButtonMinus.setVisibility(View.INVISIBLE);
                        mGoodsCount.setVisibility(View.INVISIBLE);
                        mGoodsCount.setText("0");
                        mGoodsDb.saveData(goods,mContext,"0");
                        return;
                    }
                    counter--;
                    str = String.valueOf(counter);
                    mGoodsCount.setText(str);
                    mGoodsDb.saveData(goods,mContext,str);
                }
            });

            mButtonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mButtonMinus.setVisibility(View.VISIBLE);
                    mGoodsCount.setVisibility(View.VISIBLE);
                    String str = String.valueOf(mGoodsCount.getText());
                    int counter = Integer.parseInt(str);
                    counter++;
                    str = String.valueOf(counter);
                    mGoodsCount.setText(str);
                    mGoodsDb.saveData(goods,mContext,str);

                }
            });

        }

    }

}
