package com.example.penidae_ticketing.Boat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.BoatItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BoatAdapter extends RecyclerView.Adapter<BoatAdapter.ViewHolder> {

    private Context context;
    private List<BoatItem> boatItems ;
    private OnClickListener onClickListener;

    public BoatAdapter(Context context, List<BoatItem>  boatItems) {
        this.context = context;
        this.boatItems = boatItems;
    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        BoatItem boatItem= boatItems.get(i);
        holder.bind(boatItem);
    }

    @Override
    public int getItemCount() {
        return boatItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_Image;
        TextView tv_title, tv_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_title);
            iv_Image = itemView.findViewById(R.id.item_image);
            if (onClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onClick(getAdapterPosition());
                    }
                });
            }
        }

        public void bind(BoatItem boatItem) {
            tv_title.setText(boatItem.getName());
            Glide.with(context).load(boatItem.getImage()).into(iv_Image);
        }
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
