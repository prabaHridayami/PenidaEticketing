package com.example.penidae_ticketing.VehiclePayment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.OwnerItem;
import com.example.penidae_ticketing.model.VehicleItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VehiclePaymentActivity extends AppCompatActivity {

    public static final String KEY_VEHICLE="vehicleItem";
    public static final String KEY_OWNER="ownerItem";
    VehicleItem vehicleItem;
    OwnerItem ownerItem;

    Button payment;
    TextView tv_title, tv_address, tv_price, tv_day,tv_total, tv_pickup_date, tv_return_date, tv_desc;
    String title, address, pickup_date, return_date, desc;
    Integer price, day, total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_payment);

        init();
        try {
            setText();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void init(){
        ownerItem=getIntent().getParcelableExtra(KEY_OWNER);
        vehicleItem=getIntent().getParcelableExtra(KEY_VEHICLE);

        tv_title = findViewById(R.id.tv_title);
        tv_address = findViewById(R.id.tv_address);
        tv_price = findViewById(R.id.tv_price);
        tv_day = findViewById(R.id.tv_day);
        tv_total = findViewById(R.id.tv_total);
        tv_pickup_date = findViewById(R.id.tv_pickup_date);
        tv_return_date = findViewById(R.id.tv_return_date);
        tv_desc = findViewById(R.id.tv_desc);
        payment = findViewById(R.id.rental_payment);
    }

    public void setText() throws ParseException {

        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()){
            pickup_date = bundle.getString("pickup");
            return_date = bundle.getString("re");
        }

        title = vehicleItem.getName();
        address = ownerItem.getName();
        price = vehicleItem.getPrice();
        desc = vehicleItem.getDesc();

        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        Date dt=format.parse(pickup_date);
        Date dt1=format.parse(return_date);

        String pickup          = (String) DateFormat.format("dd", dt); // 20
        String re          = (String) DateFormat.format("dd", dt1); // 20

        day = Integer.parseInt(re) - Integer.parseInt(pickup);
        total = price * day;

        tv_title.setText(title);
        tv_address.setText(address);
        tv_pickup_date.setText(pickup_date);
        tv_return_date.setText(return_date);
        tv_price.setText(Integer.toString(price));
        tv_day.setText(Integer.toString(day));
        tv_total.setText(Integer.toString(total));
        tv_desc.setText(desc);
    }
}
