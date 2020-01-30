package com.example.penidae_ticketing.Watersport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.WatersportItem;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class WatersportMainActicvity extends AppCompatActivity implements WatersportAdapter.OnClickListener, WatersportView {
    private RecyclerView recyclerAtt;
    private WatersportAdapter adapter;
    private WatersportPresenter watersportPresenter;
    List<WatersportItem> watersportItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watersport_main_acticvity);

        recyclerAtt = findViewById(R.id.rv_att);

        watersportPresenter=new WatersportPresenter(this, ApiClient.getService());
        watersportPresenter.getWatersport();
    }

    @Override
    public void onClick(int position) {
        WatersportItem watersportItem=watersportItems.get(position);
        Bundle bundle=new Bundle();
        bundle.putParcelable(WatersportDetailActivity.KEY_ATT,watersportItem);
        Intent intent=new Intent(this,WatersportDetailActivity.class);
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
    public void onSuccess(List<WatersportItem> watersportItems) {
        this.watersportItems=watersportItems;
        adapter=new WatersportAdapter(this,watersportItems);
        adapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerAtt.setLayoutManager(layoutManager);
        recyclerAtt.setAdapter(adapter);
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
