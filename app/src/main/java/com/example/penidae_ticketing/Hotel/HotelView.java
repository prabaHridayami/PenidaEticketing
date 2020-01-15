package com.example.penidae_ticketing.Hotel;

import com.example.penidae_ticketing.model.HotelItem;

import java.util.List;

public interface HotelView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<HotelItem> hotelItems,String message);
    void onError();
    void onFailure(Throwable t);
}
