package com.example.penidae_ticketing.HotelPayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.Helper.PreferenceHelper;
import com.example.penidae_ticketing.MainActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.RoomItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HotelPaymentActivity extends AppCompatActivity implements HotelPayView {
    public static final String KEY_HOTEL="hotelItem";
    private HotelPayPresenter presenter;
    private PreferenceHelper preferencesHelper;

    RoomItem roomItem;
    TextView tv_title, tv_address, tv_desc,tv_check_in_time,tv_check_out_time,tv_check_in_date,tv_check_out_date;
    TextView tv_price, tv_room, tv_total;
    String check_in, check_out, title, address, checkin_time,checkout_time,room,guest;
    Integer id, total, id_user;
    Button btn_payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_payment);
        getSupportActionBar().setElevation(0);

        init();
        setText();

        presenter = new HotelPayPresenter(this,ApiClient.getService());
        preferencesHelper=new PreferenceHelper(this);

        id_user = Integer.parseInt(preferencesHelper.getId());
        final Integer num_room = Integer.parseInt(room);


        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id_cat = roomItem.getIdCat();

                presenter.getRoomId(check_in,check_out,id_cat);

            }
        });

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
        btn_payment = findViewById(R.id.btn_payment);

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
        total = (price * num_room);

        tv_total.setText(total.toString());

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(String payment) {
        Toast.makeText(this, payment, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessId(ArrayList<Integer> roomArray) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        final String currentDateTime = sdf.format(new Date());
//
        for (int y =0; y<roomArray.size();y++){
           presenter.transHotel(roomArray.get(y),check_in,check_out,currentDateTime,total,id_user);

        }

        Toast.makeText(this, "Sukses Transaksi:"+roomArray.toString(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);


    }

    @Override
    public void onError() {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Error GetRoomId: "+t, Toast.LENGTH_SHORT).show();
    }
}
