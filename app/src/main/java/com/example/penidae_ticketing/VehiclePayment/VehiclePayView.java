package com.example.penidae_ticketing.VehiclePayment;

import java.util.ArrayList;

public interface VehiclePayView {
    void showLoading();
    void hideLoading();
    void onSuccess(Integer payment);
    void onError();
    void onFailure(Throwable t);
}
