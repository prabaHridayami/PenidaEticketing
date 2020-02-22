package com.example.penidae_ticketing.book_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.penidae_ticketing.Helper.PreferenceHelper;
import com.example.penidae_ticketing.R;
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

public class BoatFragment extends Fragment implements BookView, TransBoatAdapter.OnClickListener {

    View v;
    private RecyclerView recyclerView;
    List<TransBoatItem> transBoatItems;
    private TransBoatAdapter adapter;
    private BookPresenter bookPresenter;
    private PreferenceHelper preferencesHelper;
    Integer id_user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_boat,container,false);

        recyclerView = v.findViewById(R.id.rv_boat);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        preferencesHelper=new PreferenceHelper(getContext());
        id_user = preferencesHelper.getId();
        bookPresenter =new BookPresenter(this, ApiClient.getService());
        bookPresenter.getTransBoat(id_user);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccessBoat(List<TransBoatItem> transBoatItems) {
        this.transBoatItems = transBoatItems;
        adapter=new TransBoatAdapter(getContext(),transBoatItems);
        adapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Toast.makeText(getContext(), "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessHotel(List<TransHotelItem> transHotelItems) {

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
        TransBoatItem transBoatItem = transBoatItems.get(position);
        Toast.makeText(getContext(),Integer.toString(transBoatItem.getIdTrans()),Toast.LENGTH_LONG).show();
    }
}
