package com.example.penidae_ticketing.Tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.TourPackageItem;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TourListActivity extends AppCompatActivity implements TourView, TourAdapter.OnClickListener {

    private RecyclerView recyclerTour;
    private TourAdapter tourAdapter;
    private TourPresenter tourPresenter;
    List<TourPackageItem> tourPackageItems;

    String tourdate, guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_list);

        tourPresenter=new TourPresenter(this, ApiClient.getService());
        Bundle bundle = getIntent().getExtras();

//        if (!bundle.isEmpty()){
        tourdate= bundle.getString("tourdate");
        guest = bundle.getString("guest");

        Toast.makeText(this,tourdate +", "+guest,Toast.LENGTH_LONG).show();

        tourPresenter.getTourPack(tourdate,Integer.parseInt(guest));
//        }

        recyclerTour = findViewById(R.id.rv_tour);

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
//        Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessPay(Integer payment) {

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

    @Override
    public void onClick(int position) {
        TourPackageItem tourPackageItem=tourPackageItems.get(position);
        Bundle bundle=new Bundle();
        bundle.putParcelable(TourDetailActivity.KEY_TOUR,tourPackageItem);
        Intent intent=new Intent(this, TourDetailActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("tourdate",tourdate);
        intent.putExtra("guest",guest);
        startActivity(intent);
    }
}
