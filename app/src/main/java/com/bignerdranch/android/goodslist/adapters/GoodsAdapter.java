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
import com.bignerdranch.android.goodslist.database.App;
import com.bignerdranch.android.goodslist.pojo.Goods;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;



public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {
    private ArrayList<Goods> mGoods;
    private Context mContext;
    App App = new App();
    Goods goods;



    public GoodsAdapter(ArrayList<Goods> goods) {
        this.mGoods = goods;
    }

    @NonNull
    @Override
    public GoodsAdapter.GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent,false);
        return new GoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodsViewHolder holder, int position) {
         holder.bind(goods = mGoods.get(position));
         mContext = holder.itemView.getContext();

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
        private TextView mTitle;
        private TextView mDescription;
        private TextView mPrice;



        public GoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewGoods);
            mGoodsCount = (TextView) itemView.findViewById(R.id.goodsCount);
            mButtonPlus = (Button) itemView.findViewById(R.id.btnPlus);
            mButtonMinus = (Button) itemView.findViewById(R.id.btnMinus);
            mTitle = itemView.findViewById(R.id.goodsTitle);
            mDescription = itemView.findViewById(R.id.goodsDescription);
            mPrice = itemView.findViewById(R.id.goodsPrice);
        }

        @SuppressLint("SetTextI18n") // без этого ругается 86 строка :
        // Do not concatenate text displayed with setText. Use resource string with placeholders.
        private void bind(final Goods goods) {

            mTitle.setText(goods.getName());
            mDescription.setText(goods.getDescription());
            mPrice.setText(goods.getPrice() + mImageView.getContext().getString(R.string.rub_sign));

            Glide.with(mImageView.getContext())
                    .load(goods.getImage())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                            .centerCrop()
                            .error(R.drawable.ic_launcher_foreground))
                    .into(mImageView);

            String value = App.loadData(goods,mImageView.getContext());
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
                        App.saveData(goods,mContext,"0");
                        return;
                    }
                    counter--;
                    str = String.valueOf(counter);
                    mGoodsCount.setText(str);
                    App.saveData(goods,mContext,str);
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
                    App.saveData(goods,mContext,str);

                }
            });

        }

    }

}
