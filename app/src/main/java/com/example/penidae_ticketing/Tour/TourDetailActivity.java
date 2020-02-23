package com.example.penidae_ticketing.Tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.penidae_ticketing.Helper.PreferenceHelper;
import com.example.penidae_ticketing.HotelPayment.HotelPayPresenter;
import com.example.penidae_ticketing.MainActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.TourPackageItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TourDetailActivity extends AppCompatActivity implements TourView {
    public static final String KEY_TOUR="tourPackageItem";

    TourPackageItem tourPackageItem;

    Button payment;
    ImageView iv_tour;
    TextView tv_title, tv_price, tv_guest, tv_total, tv_tourdate, tv_maxQty, tv_desc;
    String tourdate;
    Integer total, guest, id_user;

    TourPresenter presenter;
    private PreferenceHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        init();
        setText();

        presenter = new TourPresenter(this, ApiClient.getService());
        preferencesHelper=new PreferenceHelper(this);

        id_user = preferencesHelper.getId();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        final String currentDateTime = sdf.format(new Date());

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.transTour(tourdate,total,id_user,tourPackageItem.getId(),guest,currentDateTime);
            }
        });
    }

    private void init(){
        tourPackageItem=getIntent().getParcelableExtra(KEY_TOUR);

        Bundle bundle = getIntent().getExtras();
        guest = Integer.parseInt(bundle.getString("guest"));
        tourdate = bundle.getString("tourdate");

        payment = findViewById(R.id.btn_payment);
        iv_tour = findViewById(R.id.iv_tour);
        tv_title = findViewById(R.id.tv_title);
        tv_price = findViewById(R.id.tv_price);
        tv_guest = findViewById(R.id.tv_guest);
        tv_total = findViewById(R.id.tv_total);
        tv_tourdate = findViewById(R.id.tv_tour_date);
        tv_maxQty = findViewById(R.id.tv_maxQty);
        tv_desc = findViewById(R.id.tv_desc);

        total = tourPackageItem.getPrice()* guest;

    }

    private void setText(){
        Glide.with(this).load(tourPackageItem.getImage()).centerCrop().placeholder(R.drawable.nuspen1).into(iv_tour);
        tv_title.setText(tourPackageItem.getName()+", "+tourPackageItem.getId());
        tv_price.setText(Integer.toString(tourPackageItem.getPrice()));
        tv_guest.setText(Integer.toString(guest));
        tv_total.setText(Integer.toString(total));
        tv_tourdate.setText(tourdate);
        tv_maxQty.setText(Integer.toString(tourPackageItem.getMaxQty()));
        tv_desc.setText(tourPackageItem.getDesc());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(List<TourPackageItem> tourPackageItems) {

    }

    @Override
    public void onSuccessPay(Integer payment) {
        Toast.makeText(this,"Payment Succes with ID:"+payment,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onSuccessNull(String message) {

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
