package com.example.penidae_ticketing.WatersportPayment;

public interface WatersportPayView {
    void showLoading();
    void hideLoading();
    void onSuccess(Integer payment);
    void onError();
    void onFailure(Throwable t);
}
