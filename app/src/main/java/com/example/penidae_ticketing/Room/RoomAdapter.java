package com.example.penidae_ticketing.Room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.HotelItem;
import com.example.penidae_ticketing.model.RoomItem;
import java.util.List;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private Context context;
    private List<RoomItem> roomItems ;
    private OnClickListener onClickListener;

    public RoomAdapter(Context context, List<RoomItem>  roomItems ) {
        this.context = context;
        this.roomItems = roomItems;

    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.room_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        RoomItem roomItem= roomItems.get(i);
        holder.bind(roomItem);
    }

    @Override
    public int getItemCount() {
        return roomItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_price, tv_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_title);
            tv_price = itemView.findViewById(R.id.item_price);
            tv_desc = itemView.findViewById(R.id.item_desc);
            if (onClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onClick(getAdapterPosition());
                    }
                });
            }
        }

        public void bind(RoomItem roomItem) {
            tv_title.setText(roomItem.getCategory());
            tv_price.setText(Integer.toString(roomItem.getPrice()));
            tv_desc.setText(roomItem.getDesc());

        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
