package com.example.penidae_ticketing.Tour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.AttractionItem;
import com.example.penidae_ticketing.model.TourPackageItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {

    private Context context;
    private List<TourPackageItem> tourPackageItems ;
    private OnClickListener onClickListener;

    public TourAdapter(Context context, List<TourPackageItem>  tourPackageItems) {
        this.context = context;
        this.tourPackageItems = tourPackageItems;
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
        TourPackageItem tourPackageItem= tourPackageItems.get(i);
        holder.bind(tourPackageItem);
    }

    @Override
    public int getItemCount() {
        return tourPackageItems.size();
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

        public void bind(TourPackageItem tourPackageItem) {
            tv_title.setText(tourPackageItem.getName());
            tv_price.setText(Integer.toString(tourPackageItem.getPrice()));
            Glide.with(context).load(tourPackageItem.getImage()).into(iv_Image);
        }
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
