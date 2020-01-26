package com.example.penidae_ticketing.Vehicle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.OwnerItem;
import com.example.penidae_ticketing.model.VehicleItem;

import java.util.List;

public class VehicleListActivity extends AppCompatActivity implements VehicleView, VehicleAdapter.OnClickListener   {
    public static final String KEY_VEHICLE="ownerItem";
    VehicleAdapter adapter;
    VehiclePresenter presenter;

    OwnerItem ownerItem;
    List<VehicleItem> vehicleItems;
    RecyclerView recyclerView;
    TextView header;

    Integer id;
    String pickup_date, return_date, cate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
        getSupportActionBar().setElevation(0);

        init();
        setText();
        setRecyclerView();
    }

    private void init(){
        ownerItem=getIntent().getParcelableExtra(KEY_VEHICLE);
        id = ownerItem.getId();

        recyclerView =  findViewById(R.id.rv_vehicle);
        header = findViewById(R.id.header);

        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()){
            pickup_date = bundle.getString("pickup");
            return_date = bundle.getString("re");
            cate = bundle.getString("cate");
        }



        presenter=new VehiclePresenter(this, ApiClient.getService());


    }

    private void setText(){
        header.setText("Pick Up: "+pickup_date+", Return: "+return_date+"");
    }

    private void setRecyclerView(){
        presenter.getVehicle(id,pickup_date,return_date);
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(List<OwnerItem> ownerItems) {

    }

    @Override
    public void onSuccessV(List<VehicleItem> vehicleItems) {
        this.vehicleItems=vehicleItems;
        adapter=new VehicleAdapter(this,vehicleItems);
        adapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
