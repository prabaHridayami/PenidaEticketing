package com.example.penidae_ticketing.Vehicle;

import com.example.penidae_ticketing.model.OwnerItem;
import com.example.penidae_ticketing.model.VehicleItem;

import java.util.List;

public interface VehicleView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<OwnerItem> ownerItems);
    void onSuccessV(List<VehicleItem> vehicleItems);
    void onError();
    void onFailure(Throwable t);
}
