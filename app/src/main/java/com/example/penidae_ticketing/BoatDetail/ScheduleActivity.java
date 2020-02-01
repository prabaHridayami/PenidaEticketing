package com.example.penidae_ticketing.BoatDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.BoatPayment.BoatPaymentActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.BoatItem;
import com.example.penidae_ticketing.model.ScheduleItem;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleView, ScheduleAdapter.OnClickListener{
    public static final String KEY_BOAT="boatItem";
    private static final String TAG = "Schedule" ;
    private SchedulePresenter schedulePresenter;
    private ScheduleAdapter scheduleAdapter;
    List<ScheduleItem> scheduleItems;

    TextView tv_header;
    String departure, guest;
    Integer id_boat;
    RecyclerView recyclerView;

    BoatItem boatItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().setElevation(0);

        init();
        schedulePresenter = new SchedulePresenter(this, ApiClient.getService());
        schedulePresenter.getSchedule(id_boat,departure,Integer.parseInt(guest));
    }

    private void init(){
        tv_header = findViewById(R.id.header);
        recyclerView = findViewById(R.id.rv_schedule);

        Bundle bundle = getIntent().getExtras();
        departure = bundle.getString("departure");
        guest = bundle.getString("guest");

        boatItem = getIntent().getParcelableExtra(KEY_BOAT);
        id_boat = boatItem.getId();


        tv_header.setText("Departure: "+departure);

    }

    @Override
    public void onClick(int position) {
        ScheduleItem scheduleItem=scheduleItems.get(position);
        Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
        Bundle bundle=new Bundle();
        bundle.putParcelable(BoatPaymentActivity.KEY_SCHEDULE,scheduleItem);
        Intent intent=new Intent(this, BoatPaymentActivity.class);
        intent.putExtra("departure", departure);
        intent.putExtra("guest", guest);
        intent.putExtra("title",boatItem.getName());
        intent.putExtra("desc",boatItem.getDesc());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(List<ScheduleItem> scheduleItems) {

        this.scheduleItems=scheduleItems;
        scheduleAdapter=new ScheduleAdapter(this,scheduleItems);
        scheduleAdapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(scheduleAdapter);
        Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
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
