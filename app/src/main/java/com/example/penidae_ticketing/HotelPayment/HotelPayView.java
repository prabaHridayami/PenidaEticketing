package com.example.penidae_ticketing.HotelPayment;

import com.example.penidae_ticketing.model.HotelPayment;
import com.example.penidae_ticketing.model.Payment;

import java.util.List;

public interface HotelPayView {
    void showLoading();
    void hideLoading();
    void onSuccess(String payment);
    void onError();
    void onFailure(Throwable t);
}
