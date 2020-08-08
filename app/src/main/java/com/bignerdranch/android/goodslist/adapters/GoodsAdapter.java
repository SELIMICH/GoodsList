package com.bignerdranch.android.goodslist.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.goodslist.R;
import com.bignerdranch.android.goodslist.pojo.Goods;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {

    private List<Goods> mGoods;

    public List<Goods> getGoods() {
        return mGoods;
    }

    public void setGoods(List<Goods> goods) {
        mGoods = goods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_item, parent,false);
        return new GoodsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        Goods goods = mGoods.get(position);
        holder.mTitle.setText(goods.getName());
        holder.mDescription.setText(goods.getDescription());
        holder.mPrice.setText(Integer.toString(goods.getPrice()));

        Glide.with(holder.mImageView.getContext())
                .load(goods.getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .centerCrop()
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.mImageView);
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

        public GoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.goodsTitle);
            mDescription = itemView.findViewById(R.id.goodsDescription);
            mPrice = itemView.findViewById(R.id.goodsPrice);
            mImageView = itemView.findViewById(R.id.imageViewGoods);
        }
    }
}
