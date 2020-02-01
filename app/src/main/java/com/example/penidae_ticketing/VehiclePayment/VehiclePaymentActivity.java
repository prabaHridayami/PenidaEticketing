package com.example.penidae_ticketing.VehiclePayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.Helper.PreferenceHelper;
import com.example.penidae_ticketing.MainActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.OwnerItem;
import com.example.penidae_ticketing.model.VehicleItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class VehiclePaymentActivity extends AppCompatActivity implements VehiclePayView {

    public static final String KEY_VEHICLE="vehicleItem";
    public static final String KEY_OWNER="ownerItem";
    VehicleItem vehicleItem;
    OwnerItem ownerItem;

    VehiclePayPresenter vehiclePayPresenter;
    private PreferenceHelper preferencesHelper;

    Button payment;
    TextView tv_title, tv_address, tv_price, tv_day,tv_total, tv_pickup_date, tv_return_date, tv_desc;
    String title, address, pickup_date, return_date, desc;
    Integer price, day, total, id_user;



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

        vehiclePayPresenter = new VehiclePayPresenter(this, ApiClient.getService());
        preferencesHelper=new PreferenceHelper(this);
        id_user = preferencesHelper.getId();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        final String currentDateTime = sdf.format(new Date());

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehiclePayPresenter.transVehicle(id_user,pickup_date,return_date,total,vehicleItem.getIdVehicle(), currentDateTime);
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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(Integer payment) {
        Toast.makeText(this,"Payment Succes with ID:"+payment,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @Override
    public void onError() {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Error : "+t, Toast.LENGTH_SHORT).show();
    }
}
