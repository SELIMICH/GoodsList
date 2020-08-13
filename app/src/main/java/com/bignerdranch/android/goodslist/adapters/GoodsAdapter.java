package com.bignerdranch.android.goodslist.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.goodslist.R;
import com.bignerdranch.android.goodslist.pojo.Goods;
import com.bignerdranch.android.goodslist.screens.goods.GoodsListActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {
    private ArrayList<Goods> mGoods;
    private Context mContext;
    public static final String PREF_SEARCH_ID = "searchId";
    public static final String PREF_SEARCH_VALUE = "searchId";

    public GoodsAdapter(ArrayList<Goods> goods, Context context) {
        this.mGoods = goods;
        this.mContext = context;
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
        Goods goods = mGoods.get(position);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SEARCH_ID, Context.MODE_PRIVATE);
        //Creating editor to store values to shared preferences
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        //Adding values to editor
        editor.putInt(PREF_SEARCH_ID, goods.getId());
        editor.apply();
        editor.putString(PREF_SEARCH_VALUE, String.valueOf(holder.mGoodsCount.getText()));
        editor.apply();


//        int id = sharedPreferences.getInt(PREF_SEARCH_VALUE, -1);
        String value = sharedPreferences.getString(PREF_SEARCH_VALUE, "2");
        Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();

        holder.mTitle.setText(goods.getName());
        holder.mDescription.setText(goods.getDescription());
        holder.mPrice.setText(goods.getPrice() + " ₽");
        holder.mGoodsCount.setText(value);

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
                String str = String.valueOf(holder.mGoodsCount.getText());
                int counter = Integer.parseInt(str);
                if (counter == 1) {
                    holder.mButtonMinus.setVisibility(View.INVISIBLE);
                    holder.mGoodsCount.setVisibility(View.INVISIBLE);
                    holder.mGoodsCount.setText("0");
                    return;
                }
                counter--;
                str = String.valueOf(counter);
                holder.mGoodsCount.setText(str);
            }
        });

        holder.mButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mButtonMinus.setVisibility(View.VISIBLE);
                holder.mGoodsCount.setVisibility(View.VISIBLE);
                String str = String.valueOf(holder.mGoodsCount.getText());
                int counter = Integer.parseInt(str);
                counter++;
                str = String.valueOf(counter);
                holder.mGoodsCount.setText(str);

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
