package com.example.penidae_ticketing.Tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.TourDetailActivity.TourDetailActivity;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.TourPack;
import com.example.penidae_ticketing.model.TourPackageItem;

import java.util.List;

public class TourMainActivity extends AppCompatActivity implements TourView, TourAdapter.OnClickListener {
    private RecyclerView recyclerTour;
    private TourAdapter tourAdapter;
    private TourPresenter tourPresenter;
    List<TourPackageItem> tourPackageItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_main);

        recyclerTour = findViewById(R.id.rv_tour);

        tourPresenter=new TourPresenter(this, ApiClient.getService());
        tourPresenter.getTourPack();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(List<TourPackageItem> tourPackageItems) {
        this.tourPackageItems=tourPackageItems;
        tourAdapter=new TourAdapter(this,tourPackageItems);
        tourAdapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerTour.setLayoutManager(layoutManager);
        recyclerTour.setAdapter(tourAdapter);
        Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onClick(int position) {
        TourPackageItem tourPackageItem=tourPackageItems.get(position);
//        Bundle bundle=new Bundle();
//        bundle.putParcelable(WatersportDetailActivity.KEY_HOTEL,hotelItem);
        Intent intent=new Intent(this, TourDetailActivity.class);
//        intent.putExtras(bundle);
        startActivity(intent);
    }
}
