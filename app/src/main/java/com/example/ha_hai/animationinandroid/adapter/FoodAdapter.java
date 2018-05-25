package com.example.ha_hai.animationinandroid.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ha_hai.animationinandroid.R;
import com.example.ha_hai.animationinandroid.model.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ha_hai on 5/24/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyHolder>{

    Context mContext;
    List<Food> foodList;
    RecyclerView.LayoutManager mLayoutManager;

    public FoodAdapter(Context context, List<Food> foodList, RecyclerView.LayoutManager layoutManager) {
        this.mContext = context;
        this.foodList = foodList;
        this.mLayoutManager = layoutManager;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyHolder(inflater.inflate(R.layout.row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if (mLayoutManager instanceof GridLayoutManager) {
            Picasso.get().load(foodList.get(position).getThumbnail()).into(holder.imgThumbnail);
            holder.txtName.setVisibility(View.GONE);
            holder.txtDescrition.setVisibility(View.GONE);
            holder.txtPrice.setVisibility(View.GONE);
        } else if (mLayoutManager instanceof LinearLayoutManager) {
            Picasso.get().load(foodList.get(position).getThumbnail()).into(holder.imgThumbnail);
            holder.txtName.setText(foodList.get(position).getName());
            holder.txtDescrition.setText(foodList.get(position).getDescription());
            holder.txtPrice.setText(foodList.get(position).getPrice() + "$");
        }
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public RelativeLayout foregroundView, backgroundView;
        public CardView rootView;
        public ImageView imgThumbnail;
        public TextView txtName, txtDescrition, txtPrice;

        public MyHolder(View itemView) {
            super(itemView);

            rootView = itemView.findViewById(R.id.root_view);
            foregroundView = itemView.findViewById(R.id.foreground_view);
            backgroundView = itemView.findViewById(R.id.background_view);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescrition = itemView.findViewById(R.id.txtDescrition);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
