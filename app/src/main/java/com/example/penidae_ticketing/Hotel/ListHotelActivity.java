package com.example.penidae_ticketing.Hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.Home.HotelAdapter;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.HotelItem;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ListHotelActivity extends AppCompatActivity implements HotelAdapter.OnClickListener, HotelView {
    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;
    List<HotelItem> hotelItems;
    private HotelPresenter hotelPresenter;
    TextView header;
    String check_in, check_out, guest,room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotel);
        getSupportActionBar().setElevation(0);

        header = findViewById(R.id.header);
        recyclerView = findViewById(R.id.rv_hotel);
        hotelPresenter = new HotelPresenter(this, ApiClient.getService());


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            check_in = bundle.getString("check_in");
            check_out = bundle.getString("check_out");
            guest = bundle.getString("guest");
            room = bundle.getString("room");

            header.setText("check-in: "+check_in+", check-out :"+check_out+"");
            hotelPresenter.searchHotel(check_in,check_out,guest,room);
        }
    }

    @Override
    public void onClick(int position) {
        HotelItem hotelItem=hotelItems.get(position);
        Bundle bundle=new Bundle();
        bundle.putParcelable(HotelDetailActivity.KEY_HOTEL,hotelItem);
        Intent intent=new Intent(this, HotelDetailActivity.class);
        intent.putExtra("check_in", check_in);
        intent.putExtra("check_out", check_out);
        intent.putExtra("guest",guest);
        intent.putExtra("room",room);
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
    public void onSuccess(List<HotelItem> hotelItems) {
        this.hotelItems=hotelItems;
        hotelAdapter=new HotelAdapter(this,hotelItems);
        hotelAdapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(hotelAdapter);
        Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d(TAG, "onFailure: "+t);
        Toast.makeText(this, "Error : "+t, Toast.LENGTH_SHORT).show();
    }
}
