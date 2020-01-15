package com.example.penidae_ticketing.Hotel;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.Room.RoomActivity;
import com.example.penidae_ticketing.model.HotelItem;

public class HotelDetailActivity extends AppCompatActivity {
    public static final String KEY_HOTEL="hotelItem";
    HotelItem hotelItem;

    ImageView iv_hotel;
    TextView tv_title, tv_address, tv_desc, header,tv_check_in,tv_check_out;
    Button selectRoom;

    String check_in, check_out, guest, room;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        getSupportActionBar().setElevation(0);

        init();
        setView();


        selectRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RoomActivity.class);
                intent.putExtra("id",hotelItem.getIdHotel());
                intent.putExtra("check_in", check_in);
                intent.putExtra("check_out", check_out);
                intent.putExtra("title",hotelItem.getHotelName());
                intent.putExtra("address",hotelItem.getHotelAddress());
                intent.putExtra("checkin_time",hotelItem.getCheckin_time());
                intent.putExtra("checkout_time",hotelItem.getCheckout_time());
                intent.putExtra("guest",guest);
                intent.putExtra("room",room);
                startActivity(intent);
            }
        });
    }

    private void init(){
        hotelItem=getIntent().getParcelableExtra(KEY_HOTEL);

        iv_hotel = findViewById(R.id.iv_hotel);
        tv_title = findViewById(R.id.tv_title);
        tv_address = findViewById(R.id.tv_address);
        tv_desc = findViewById(R.id.tv_desc);
        selectRoom = findViewById(R.id.selectRoom);
        header = findViewById(R.id.header);
        tv_check_in=findViewById(R.id.tv_check_in);
        tv_check_out = findViewById(R.id.tv_check_out);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            check_in = bundle.getString("check_in");
            check_out = bundle.getString("check_out");
            guest = bundle.getString("guest");
            room= bundle.getString("room");

            header.setText("check-in: "+check_in+", check-out :"+check_out+", room:"+room);
        }
    }

    private void setView(){
        tv_title.setText(hotelItem.getHotelName());
        tv_address.setText(hotelItem.getHotelAddress());
        tv_desc.setText(hotelItem.getDesc());
        tv_check_in.setText(hotelItem.getCheckin_time());
        tv_check_out.setText(hotelItem.getCheckout_time());
    }
}
