package com.example.penidae_ticketing.HotelPayment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.RoomItem;

public class HotelPaymentActivity extends AppCompatActivity {
    public static final String KEY_HOTEL="hotelItem";
    RoomItem roomItem;
    TextView tv_title, tv_address, tv_desc,tv_check_in_time,tv_check_out_time,tv_check_in_date,tv_check_out_date;
    TextView tv_price, tv_room, tv_total;
    String check_in, check_out, title, address, checkin_time,checkout_time,room,guest;
    Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_payment);
        getSupportActionBar().setElevation(0);

        init();
        setText();

    }

    private void init(){


        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        address = bundle.getString("address");
        check_in = bundle.getString("check_in");
        check_out = bundle.getString("check_out");
        checkin_time = bundle.getString("checkin_time");
        checkout_time = bundle.getString("checkout_time");
        room = bundle.getString("room");
        guest = bundle.getString("guest");
        id = bundle.getInt("id");

        tv_title = findViewById(R.id.tv_title);
        tv_address = findViewById(R.id.tv_address);
        tv_desc = findViewById(R.id.tv_desc);
        tv_check_in_time = findViewById(R.id.tv_check_in_time);
        tv_check_out_time = findViewById(R.id.tv_check_out_time);
        tv_check_in_date = findViewById(R.id.tv_check_in_date);
        tv_check_out_date = findViewById(R.id.tv_check_out_date);
        tv_price = findViewById(R.id.tv_price);
        tv_room = findViewById(R.id.tv_room);
        tv_total = findViewById(R.id.tv_total);

    }

    private void setText(){
        roomItem=getIntent().getParcelableExtra(KEY_HOTEL);

        tv_title.setText(title);
        tv_address.setText(address);
        tv_desc.setText(roomItem.getDesc());
        tv_check_in_date.setText(check_in);
        tv_check_out_date.setText(check_out);
        tv_check_in_time.setText(checkin_time);
        tv_check_out_time.setText(checkout_time);
        tv_price.setText(roomItem.getPrice()+"");
        tv_room.setText(room);

        Integer price = roomItem.getPrice();
        Integer num_room = Integer.parseInt(room);
        Integer total = (price * num_room);

        tv_total.setText(total.toString());

    }
}
