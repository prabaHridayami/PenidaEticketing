package com.example.penidae_ticketing.auth;

import com.example.penidae_ticketing.model.Auth;
import com.example.penidae_ticketing.model.User;

public interface AuthView {
    void showLoading();
    void hideLoading();
    void onSuccess(User user);
    void onError();
    void onFailure(Throwable t);
}
