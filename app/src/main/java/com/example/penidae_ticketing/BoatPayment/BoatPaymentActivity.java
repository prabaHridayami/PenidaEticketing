package com.example.penidae_ticketing.BoatPayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.BoatDetail.SchedulePresenter;
import com.example.penidae_ticketing.Helper.PreferenceHelper;
import com.example.penidae_ticketing.MainActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.ScheduleItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoatPaymentActivity extends AppCompatActivity implements BoatPayView {
    public static final String KEY_SCHEDULE="scheduleItem";
    private BoatPayPresenter boatPayPresenter;
    private PreferenceHelper preferencesHelper;
    TextView tv_title, tv_desc,tv_price,tv_guest,tv_total,tv_pickup,tv_dropup,tv_pickup_date,tv_pickup_time;
    String depart_date;
    Integer total,guest,id_user;
    Button btn_payment;

    ScheduleItem scheduleItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_payment);

        init();
        setText();

        preferencesHelper=new PreferenceHelper(this);
        id_user = preferencesHelper.getId();


        boatPayPresenter = new BoatPayPresenter(this, ApiClient.getService());
        final Bundle bundle = getIntent().getExtras();
        depart_date = bundle.getString("departure");
        guest = Integer.parseInt(bundle.getString("guest"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        final String currentDateTime = sdf.format(new Date());
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boatPayPresenter.transBoat(scheduleItem.getIdSchedule(),depart_date,currentDateTime,guest,total,id_user);
            }
        });

    }

    private void init(){
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_desc);
        tv_price = findViewById(R.id.tv_price);
        tv_guest = findViewById(R.id.tv_guest);
        tv_total = findViewById(R.id.tv_total);
        tv_pickup = findViewById(R.id.tv_pickup);
        tv_dropup = findViewById(R.id.tv_dropup);
        tv_pickup_date = findViewById(R.id.tv_pickup_date);
        tv_pickup_time = findViewById(R.id.tv_pickup_time);
        btn_payment = findViewById(R.id.btn_payment);
    }

    private void setText(){
        Bundle bundle = getIntent().getExtras();
        scheduleItem=getIntent().getParcelableExtra(KEY_SCHEDULE);

        tv_title.setText(bundle.getString("title"));
        tv_desc.setText(bundle.getString("desc"));
        tv_guest.setText(bundle.getString("guest"));
        tv_price.setText(Integer.toString(scheduleItem.getPrice()));

        total = scheduleItem.getPrice()*Integer.parseInt(bundle.getString("guest"));
        tv_total.setText(total.toString());

        tv_pickup.setText(scheduleItem.getPickupLoc());
        tv_dropup.setText(scheduleItem.getDropupLoc());
        tv_pickup_date.setText(bundle.getString("departure"));
        tv_pickup_time.setText(scheduleItem.getTime());

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
