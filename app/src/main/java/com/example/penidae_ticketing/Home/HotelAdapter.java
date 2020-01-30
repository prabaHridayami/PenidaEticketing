package com.example.penidae_ticketing.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.HotelItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private Context context;
    private List<HotelItem> hotelItems ;
    private OnClickListener onClickListener;

    public HotelAdapter(Context context, List<HotelItem>  hotelItems ) {
        this.context = context;
        this.hotelItems = hotelItems;

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
        HotelItem hotelItem= hotelItems.get(i);
        holder.bind(hotelItem);
    }

    @Override
    public int getItemCount() {
        return hotelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_Image;
        TextView tv_title, tv_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_title);
            tv_price = itemView.findViewById(R.id.item_price);
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

        public void bind(HotelItem hotelItem) {
            tv_title.setText(hotelItem.getHotelName());
            tv_price.setText(Integer.toString(hotelItem.getPrice()));
            Glide.with(context).load(hotelItem.getHotelImage()).into(iv_Image);
        }
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
