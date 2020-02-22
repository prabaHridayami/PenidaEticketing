package com.example.penidae_ticketing.book_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.HotelItem;
import com.example.penidae_ticketing.model.TransBoatItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransBoatAdapter extends RecyclerView.Adapter<TransBoatAdapter.ViewHolder> {

    private Context context;
    private List<TransBoatItem> transBoatItems ;
    private OnClickListener onClickListener;

    public TransBoatAdapter(Context context, List<TransBoatItem>  transBoatItems ) {
        this.context = context;
        this.transBoatItems = transBoatItems;

    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.book_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        TransBoatItem transBoatItem= transBoatItems.get(i);
        holder.bind(transBoatItem);
    }

    @Override
    public int getItemCount() {
        return transBoatItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_Image;
        TextView tv_title, tv_price, tv_status;
        Button btn_upload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_title);
            tv_price = itemView.findViewById(R.id.item_price);
            iv_Image = itemView.findViewById(R.id.item_image);
            tv_status = itemView.findViewById(R.id.item_status);
            btn_upload = itemView.findViewById(R.id.btn_upload);
            if (onClickListener!=null){
                btn_upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onClick(getAdapterPosition());
                    }
                });
            }
        }

        public void bind(TransBoatItem transBoatItem) {
            tv_title.setText(transBoatItem.getName());
            tv_price.setText(Integer.toString(transBoatItem.getTotalPrice()));
            tv_status.setText(transBoatItem.getStatus());
            Glide.with(context).load(transBoatItem.getBoatImage()).into(iv_Image);
        }
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
