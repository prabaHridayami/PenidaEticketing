package com.example.penidae_ticketing.Boat;

import com.example.penidae_ticketing.model.BoatItem;

import java.util.List;

public interface BoatView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<BoatItem> boatItems);
    void onError();
    void onFailure(Throwable t);
}
