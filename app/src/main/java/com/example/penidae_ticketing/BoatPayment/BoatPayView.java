package com.example.penidae_ticketing.BoatPayment;

import java.util.ArrayList;

public interface BoatPayView {
    void showLoading();
    void hideLoading();
    void onSuccess(Integer payment);
    void onError();
    void onFailure(Throwable t);
}
