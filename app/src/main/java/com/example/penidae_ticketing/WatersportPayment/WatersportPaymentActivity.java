package com.example.penidae_ticketing.WatersportPayment;

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
import com.example.penidae_ticketing.VehiclePayment.VehiclePayPresenter;
import com.example.penidae_ticketing.Watersport.WatersportPresenter;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.AttractionItem;
import com.example.penidae_ticketing.model.WatersportItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WatersportPaymentActivity extends AppCompatActivity implements WatersportPayView{
    public static final String KEY_WATERSPORT="watersportItem";
    public static final String KEY_ATT="attractionItem";

    WatersportItem watersportItem;
    AttractionItem attractionItem;

    WatersportPayPresenter watersportPayPresenter;
    private PreferenceHelper preferencesHelper;

    TextView tv_title, tv_address, tv_price, tv_ticket, tv_valid, tv_desc, tv_total, tv_att, tv_descatt;
    Integer price, ticket, total, id_user;
    String playdate;
    Button payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watersport_payment);

        init();
        setText();

        watersportPayPresenter = new WatersportPayPresenter(this,ApiClient.getService());
        preferencesHelper=new PreferenceHelper(this);
        id_user = preferencesHelper.getId();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        final String currentDateTime = sdf.format(new Date());

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watersportPayPresenter.transWatersport(attractionItem.getId(), currentDateTime,ticket,playdate,total,id_user);
            }
        });

    }

    private void init(){
        watersportItem=getIntent().getParcelableExtra(KEY_WATERSPORT);
        attractionItem=getIntent().getParcelableExtra(KEY_ATT);

        Bundle bundle = getIntent().getExtras();
        ticket = bundle.getInt("ticket");
        playdate = bundle.getString("playdate");
        total = attractionItem.getPrice()*ticket;

        tv_title = findViewById(R.id.tv_title);
        tv_address = findViewById(R.id.tv_address);
        tv_price = findViewById(R.id.tv_price);
        tv_ticket = findViewById(R.id.tv_ticket);
        tv_valid = findViewById(R.id.tv_valid);
        tv_desc = findViewById(R.id.tv_desc);
        tv_total = findViewById(R.id.tv_total);
        tv_att = findViewById(R.id.tv_att);
        tv_descatt = findViewById(R.id.tv_descatt);
        payment = findViewById(R.id.btn_payment);

    }

    private void setText(){
        tv_title.setText(watersportItem.getName());
        tv_address.setText(watersportItem.getAddress());
        tv_att.setText(attractionItem.getName());
        tv_descatt.setText(attractionItem.getDesc());
        tv_price.setText(Integer.toString(attractionItem.getPrice()));
        tv_valid.setText(playdate);
        tv_ticket.setText(Integer.toString(ticket));
        tv_total.setText(Integer.toString(total));
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
