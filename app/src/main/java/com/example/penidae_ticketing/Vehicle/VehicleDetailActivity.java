package com.example.penidae_ticketing.Vehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.Vehicle.VehicleListActivity;
import com.example.penidae_ticketing.model.OwnerItem;

public class VehicleDetailActivity extends AppCompatActivity {
    public static final String KEY_VEHICLE="ownerItem";
    TextView tv_title, tv_address, tv_pickup_date, tv_return_date, tv_pickup_time, tv_return_time, tv_desc;
    String title, address, pickup_date, return_date, pickup_time, return_time, desc, image, cate;

    Button rent;

    OwnerItem ownerItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        init();
        setText();

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putParcelable(VehicleListActivity.KEY_VEHICLE,ownerItem);
                Intent intent = new Intent(getApplicationContext(), VehicleListActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("pickup",pickup_date);
                intent.putExtra("re",return_date);
                intent.putExtra("cate",cate);
                startActivity(intent);
            }
        });
    }

    private void init(){
        ownerItem=getIntent().getParcelableExtra(KEY_VEHICLE);

        tv_title = findViewById(R.id.tv_title);
        tv_address = findViewById(R.id.tv_address);
        tv_pickup_date = findViewById(R.id.tv_pickup_date);
        tv_return_date = findViewById(R.id.tv_return_date);
        tv_pickup_time = findViewById(R.id.tv_pickup_time);
        tv_return_time = findViewById(R.id.tv_return_time);
        tv_desc = findViewById(R.id.tv_desc);
        rent = findViewById(R.id.rent);

        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()){
            pickup_date = bundle.getString("pickup");
            return_date = bundle.getString("re");
            cate = bundle.getString("cate");
        }

        title = ownerItem.getName();
        address = ownerItem.getAddress();
        desc = ownerItem.getDesc();
        image = ownerItem.getImage();

    }

    private void setText(){

        tv_title.setText(title);
        tv_address.setText(address);
        tv_desc.setText(desc);
        tv_pickup_date.setText(pickup_date);
        tv_return_date.setText(return_date);

    }
}
