package com.example.penidae_ticketing.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.penidae_ticketing.Boat.BoatMainActivity;
import com.example.penidae_ticketing.Hotel.HotelMainActivity;
import com.example.penidae_ticketing.Hotel.HotelDetailActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.Tour.TourMainActivity;
import com.example.penidae_ticketing.Vehicle.VehicleMainActivity;
import com.example.penidae_ticketing.Watersport.WatersportMainActicvity;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.HotelItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment implements HotelAdapter.OnClickListener, View.OnClickListener, HomeView{

    private ImageView imageRun;
    private Handler handler;
    private Runnable changeImage;
    private RecyclerView recyclerHotel;
    private HotelAdapter adapter;
    private HomePresenter homePresenter;
    ImageButton btn_boat, btn_hotel, btn_vehicle, btn_watersport, btn_tour;
    List<HotelItem> hotelItems;

    View v;


    private int[] images ={R.drawable.nuspen1,R.drawable.nuspen2,R.drawable.nuspen3,R.drawable.nuspen4,R.drawable.nuspen5};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerHotel = v.findViewById(R.id.rv_boat);
        btn_boat = v.findViewById(R.id.btn_boat);
        btn_hotel = v.findViewById(R.id.btn_hotel);
        btn_vehicle = v.findViewById(R.id.btn_vehicle);
        btn_watersport = v.findViewById(R.id.btn_watersport);
        btn_tour = v.findViewById(R.id.btn_tour);

        btn_hotel.setOnClickListener(this);
        btn_boat.setOnClickListener(this);
        btn_vehicle.setOnClickListener(this);
        btn_watersport.setOnClickListener(this);
        btn_tour.setOnClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        homePresenter=new HomePresenter(this, ApiClient.getService());
        homePresenter.getHotel();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerHotel = v.findViewById(R.id.rv_boat);
        imageRun = v.findViewById(R.id.image_run);

        handler = new Handler();
        changeImage = new Runnable(){
            int flag = 0;
            @Override
            public void run() {
                imageRun.setImageResource(images[flag]);
                handler.postDelayed(changeImage, 3000);
                flag++;
                if (flag==(images.length)){
                    flag = 0;
                }
            }
        };
        changeImage.run();

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
        adapter=new HotelAdapter(getContext(),hotelItems);
        adapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerHotel.setLayoutManager(layoutManager);
        recyclerHotel.setAdapter(adapter);
        Toast.makeText(getContext(), "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d(TAG, "onFailure: "+t);
        Toast.makeText(getContext(), "Error : "+t, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(int position) {
        HotelItem hotelItem=hotelItems.get(position);
        Bundle bundle=new Bundle();
//        bundle.putParcelable(HotelDetailActivity.KEY_HOTEL,hotelItem);
        Intent intent=new Intent(getContext(),HotelDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_boat:
                intent = new Intent(getContext(), BoatMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_hotel:
                intent = new Intent(getContext(), HotelMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_vehicle:
                intent = new Intent(getContext(), VehicleMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_watersport:
                intent = new Intent(getContext(), WatersportMainActicvity.class);
                startActivity(intent);
                break;
            case R.id.btn_tour:
                intent = new Intent(getContext(), TourMainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
