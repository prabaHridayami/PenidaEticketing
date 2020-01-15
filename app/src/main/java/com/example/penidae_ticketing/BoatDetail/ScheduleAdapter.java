package com.example.penidae_ticketing.BoatDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.RoomItem;
import com.example.penidae_ticketing.model.ScheduleItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private Context context;
    private List<ScheduleItem> scheduleItems ;
    private OnClickListener onClickListener;

    public ScheduleAdapter(Context context, List<ScheduleItem>  scheduleItems ) {
        this.context = context;
        this.scheduleItems = scheduleItems;

    }

    public interface OnClickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.boat_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ScheduleItem scheduleItem= scheduleItems.get(i);
        holder.bind(scheduleItem);
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_pickup, tv_dropup, tv_time, tv_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pickup = itemView.findViewById(R.id.item_pickup);
            tv_dropup = itemView.findViewById(R.id.item_drop);
            tv_time = itemView.findViewById(R.id.item_time);
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

        public void bind(ScheduleItem scheduleItem) {
            tv_pickup.setText(scheduleItem.getPickupLoc());
            tv_dropup.setText(scheduleItem.getDropupLoc());
            tv_time.setText(scheduleItem.getTime());
            tv_price.setText(Integer.toString(scheduleItem.getPrice()));

        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
