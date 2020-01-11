package com.example.penidae_ticketing.Room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.HotelPayment.HotelPaymentActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.RoomItem;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RoomActivity extends AppCompatActivity implements RoomView, RoomAdapter.OnClickListener {

    private RecyclerView recyclerRoom;
    private RoomAdapter roomAdapter;
    private RoomPresenter roomPresenter;
    List<RoomItem> roomItems;

    TextView header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        getSupportActionBar().setElevation(0);

        header = findViewById(R.id.header);
        recyclerRoom = findViewById(R.id.rv_room);

        roomPresenter = new RoomPresenter(this, ApiClient.getService());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String check_in = bundle.getString("check_in");
            String check_out = bundle.getString("check_out");
            Integer id = bundle.getInt("id");

            header.setText("check-in: "+check_in+", check-out :"+check_out+"");
            roomPresenter.getRoom(check_in,check_out,id);
        }


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(List<RoomItem> roomItems) {
        this.roomItems=roomItems;
        roomAdapter=new RoomAdapter(this,roomItems);
        roomAdapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerRoom.setLayoutManager(layoutManager);
        recyclerRoom.setAdapter(roomAdapter);
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

    @Override
    public void onClick(int position) {
        RoomItem roomItem=roomItems.get(position);
        Bundle bundle=new Bundle();
//        bundle.putParcelable(HotelDetailActivity.KEY_HOTEL,hotelItem);
        Intent intent=new Intent(this, HotelPaymentActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
