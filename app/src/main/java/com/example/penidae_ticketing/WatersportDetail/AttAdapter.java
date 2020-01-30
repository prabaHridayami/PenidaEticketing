package com.example.penidae_ticketing.WatersportDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.Attraction;
import com.example.penidae_ticketing.model.AttractionItem;
import com.example.penidae_ticketing.model.WatersportItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AttAdapter extends RecyclerView.Adapter<AttAdapter.ViewHolder> {

    private Context context;
    private List<AttractionItem> attractionItems ;
    private OnClickListener onClickListener;

    public AttAdapter(Context context, List<AttractionItem>  attractionItems) {
        this.context = context;
        this.attractionItems = attractionItems;
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
        AttractionItem attractionItem= attractionItems.get(i);
        holder.bind(attractionItem);
    }

    @Override
    public int getItemCount() {
        return attractionItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_Image;
        TextView tv_title, tv_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_title);
            iv_Image = itemView.findViewById(R.id.item_image);
            tv_price = itemView.findViewById(R.id.item_price);
            if (onClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onClick(getAdapterPosition());
                    }
                });
            }
        }

        public void bind(AttractionItem attractionItem) {
            tv_title.setText(attractionItem.getName());
            tv_price.setText(Integer.toString(attractionItem.getPrice()));
            Glide.with(context).load(attractionItem.getImage()).into(iv_Image);
        }
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
