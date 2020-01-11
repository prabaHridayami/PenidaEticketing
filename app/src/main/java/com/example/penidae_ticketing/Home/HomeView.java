package com.example.penidae_ticketing.Home;

import com.example.penidae_ticketing.model.HotelItem;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<HotelItem> hotelItems);
    void onError();
    void onFailure(Throwable t);

}
