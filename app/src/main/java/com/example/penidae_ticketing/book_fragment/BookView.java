package com.example.penidae_ticketing.book_fragment;

import com.example.penidae_ticketing.model.TransBoatItem;
import com.example.penidae_ticketing.model.TransHotelItem;
import com.example.penidae_ticketing.model.TransRentItem;
import com.example.penidae_ticketing.model.TransTourItem;
import com.example.penidae_ticketing.model.TransWatersportItem;

import java.util.List;

public interface BookView {
    void showLoading();
    void hideLoading();
    void onSuccessBoat(List<TransBoatItem> transBoatItems);
    void onSuccessHotel(List<TransHotelItem> transHotelItems);
    void onSuccessVehicle(List<TransRentItem> transRentItems);
    void onSuccessWatersport(List<TransWatersportItem> watersportItems);
    void onSuccessTour(List<TransTourItem> transTourItems);
    void onError();
    void onFailure(Throwable t);

}
