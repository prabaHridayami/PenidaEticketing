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
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.OwnerItem;
import com.example.penidae_ticketing.model.VehicleItem;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ListRentalActivity extends AppCompatActivity implements VehicleView, RentalListAdapter.OnClickListener {
    TextView header;
    String pickup, re,cate;

    List<OwnerItem> ownerItems;
    RentalListAdapter adapter;
    RecyclerView recyclerView;
    private VehiclePresenter vehiclePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rental);
        getSupportActionBar().setElevation(0);

        init();
        setText();


        vehiclePresenter=new VehiclePresenter(this, ApiClient.getService());
        vehiclePresenter.getRental(pickup,re,cate);

    }

    private void init(){
        header = findViewById(R.id.header);
        recyclerView =  findViewById(R.id.rv_rental);
    }

    private void setText(){
        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()){
            pickup = bundle.getString("pickup");
            re = bundle.getString("re");
            cate = bundle.getString("cate");
        }

        header.setText("Pick Up: "+pickup+", Return: "+re+"");
    }

    @Override
    public void onClick(int position) {
        OwnerItem ownerItem=ownerItems.get(position);
        Bundle bundle=new Bundle();
        bundle.putParcelable(VehicleDetailActivity.KEY_VEHICLE,ownerItem);
        Intent intent=new Intent(this,VehicleDetailActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("pickup",pickup);
        intent.putExtra("re",re);
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
        this.ownerItems=ownerItems;
        adapter=new RentalListAdapter(this,ownerItems);
        adapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessV(List<VehicleItem> vehicleItems) {

    }

    @Override
    public void onSuccessNull(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
