package com.bignerdranch.android.goodslist.adapters;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.goodslist.R;
import com.bignerdranch.android.goodslist.pojo.Goods;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {
    private ArrayList<Goods> mGoods;

    public GoodsAdapter(ArrayList<Goods> goods) {
        this.mGoods = goods;
    }

    @NonNull
    @Override
    public GoodsAdapter.GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent,false);
        return new GoodsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final GoodsViewHolder holder, int position) {
        Goods goods = mGoods.get(position);
        holder.mTitle.setText(goods.getName());
        holder.mDescription.setText(goods.getDescription());
        holder.mPrice.setText(goods.getPrice() + " ₽");

        Glide.with(holder.mImageView.getContext())
                .load(goods.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .centerCrop()
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.mImageView);

        holder.mButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counterTemp = 0;
                if (counterTemp != 0) {
                    counterTemp--;
                    String counter = String.valueOf(counterTemp);
                    holder.mGoodsCount.setText(counter);
                }else  {
                    holder.mButtonMinus.setVisibility(View.INVISIBLE);
                }
            }
        });

        holder.mButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counterTemp = 0;
                counterTemp++;
                String counter = String.valueOf(counterTemp);
                holder.mGoodsCount.setText(counter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoods.size();
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mDescription;
        private TextView mPrice;
        private ImageView mImageView;
        private TextView mGoodsCount;
        private Button mButtonMinus;
        private Button mButtonPlus;



        public GoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.goodsTitle);
            mDescription = itemView.findViewById(R.id.goodsDescription);
            mPrice = itemView.findViewById(R.id.goodsPrice);
            mImageView = itemView.findViewById(R.id.imageViewGoods);
            mGoodsCount = (TextView) itemView.findViewById(R.id.goodsCount);
            mButtonPlus = (Button) itemView.findViewById(R.id.btnPlus);
            mButtonMinus = (Button) itemView.findViewById(R.id.btnMinus);

            

        }

    }


}
