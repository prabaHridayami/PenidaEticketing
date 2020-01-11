package com.example.penidae_ticketing.Vehicle;

import com.example.penidae_ticketing.model.OwnerItem;

import java.util.List;

public interface VehicleView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<OwnerItem> ownerItems);
    void onError();
    void onFailure(Throwable t);
}
