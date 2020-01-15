package com.example.penidae_ticketing.HotelPayment;

import java.util.ArrayList;

public interface HotelPayView {
    void showLoading();
    void hideLoading();
    void onSuccess(String payment);
    void onSuccessId(ArrayList<Integer> roomArray);
    void onError();
    void onFailure(Throwable t);
}
