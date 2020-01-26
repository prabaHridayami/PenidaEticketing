package com.example.penidae_ticketing.VehiclePayment;

import java.util.ArrayList;

public interface VehiclePayView {
    void showLoading();
    void hideLoading();
    void onSuccess(String payment);
    void onSuccessId(ArrayList<Integer> roomArray);
    void onError();
    void onFailure(Throwable t);
}
