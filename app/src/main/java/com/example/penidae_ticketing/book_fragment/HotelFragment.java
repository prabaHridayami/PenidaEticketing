package com.example.penidae_ticketing.book_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.penidae_ticketing.Helper.PreferenceHelper;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.ReceiptActivity.UploadActivity;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.TransBoatItem;
import com.example.penidae_ticketing.model.TransHotelItem;
import com.example.penidae_ticketing.model.TransRentItem;
import com.example.penidae_ticketing.model.TransTourItem;
import com.example.penidae_ticketing.model.TransWatersportItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HotelFragment extends Fragment implements BookView, TransHotelAdapter.OnClickListener {

    View v;
    private RecyclerView recyclerView;
    List<TransHotelItem> transHotelItemList;
    private TransHotelAdapter adapter;
    private BookPresenter bookPresenter;
    private PreferenceHelper preferencesHelper;
    Integer id_user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_hotel,container,false);

        recyclerView = v.findViewById(R.id.rv_hotel);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        preferencesHelper=new PreferenceHelper(getContext());
        id_user = preferencesHelper.getId();
        bookPresenter =new BookPresenter(this, ApiClient.getService());
        bookPresenter.getTransHotel(id_user);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccessBoat(List<TransBoatItem> transBoatItems) {

    }

    @Override
    public void onSuccessHotel(List<TransHotelItem> transHotelItems) {
        this.transHotelItemList = transHotelItems;
        adapter= new TransHotelAdapter(getContext(), transHotelItems);
        adapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Toast.makeText(getContext(), "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessVehicle(List<TransRentItem> transRentItems) {

    }

    @Override
    public void onSuccessWatersport(List<TransWatersportItem> watersportItems) {

    }

    @Override
    public void onSuccessTour(List<TransTourItem> transTourItems) {

    }

    @Override
    public void onEmpty() {
        Toast.makeText(getContext(), "You haven't make any booking yet! ", Toast.LENGTH_SHORT).show();
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
        TransHotelItem transHotelItem = transHotelItemList.get(position);
        if (transHotelItem.getStatus().equals("success")){
            Toast.makeText(getContext(),"Reciept has uploaded",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getContext(), UploadActivity.class);
            intent.putExtra("menu","hotel");
            intent.putExtra("id",transHotelItem.getIdTrans());
            startActivity(intent);
        }
    }
}
