package com.example.penidae_ticketing.Vehicle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.VehiclePayment.VehiclePaymentActivity;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.OwnerItem;
import com.example.penidae_ticketing.model.VehicleItem;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

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
        VehicleItem vehicleItem=vehicleItems.get(position);
        Bundle bundle=new Bundle();
        bundle.putParcelable(VehiclePaymentActivity.KEY_VEHICLE,vehicleItem);
        Bundle owner = new Bundle();
        owner.putParcelable(VehiclePaymentActivity.KEY_OWNER,ownerItem);
        Intent intent=new Intent(this,VehiclePaymentActivity.class);
        intent.putExtras(bundle);
        intent.putExtras(owner);
        intent.putExtra("pickup",pickup_date);
        intent.putExtra("re",return_date);
        intent.putExtra("cate",cate);
        startActivity(intent);
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
    public void onSuccessNull(String message) {

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
