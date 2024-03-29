package com.example.penidae_ticketing.Vehicle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.OwnerItem;
import com.example.penidae_ticketing.model.VehicleItem;

import org.xml.sax.Parser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    private Context context;
    private List<VehicleItem> vehicleItems ;
    private OnClickListener onClickListener;

    public VehicleAdapter(Context context, List<VehicleItem>  vehicleItems) {
        this.context = context;
        this.vehicleItems = vehicleItems;
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
        VehicleItem vehicleItem= vehicleItems.get(i);
        holder.bind(vehicleItem);
    }

    @Override
    public int getItemCount() {
        return vehicleItems.size();
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

        public void bind(VehicleItem vehicleItem) {

            tv_title.setText(vehicleItem.getName());
            tv_price.setText(Integer.toString(vehicleItem.getPrice()));
        }
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
