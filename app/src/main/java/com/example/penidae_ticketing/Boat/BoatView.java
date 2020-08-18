package com.example.penidae_ticketing.Boat;

import com.example.penidae_ticketing.model.BoatItem;

import java.util.List;

public interface BoatView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<BoatItem> boatItems);
    void onEmpty(String message);
    void onError();
    void onFailure(Throwable t);
}
