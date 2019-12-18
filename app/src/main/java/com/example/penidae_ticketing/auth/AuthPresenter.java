package com.example.penidae_ticketing.auth;

import android.util.Log;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Auth;
import com.example.penidae_ticketing.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class AuthPresenter {
    private AuthView view;
    private ApiService service;

    public AuthPresenter(AuthView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void login(String username, String password){
        view.showLoading();
        service.login(username,password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body());
                }else {
                    view.onError(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.onFailure(t);
                Log.d(TAG, "onFailure: "+t);
                view.hideLoading();
            }
        });
    }

    public void register(String name, String username,String email,String password, String phone){
        view.showLoading();
        service.register(name,username,email,password,phone).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body());
                }else {
                    view.onError(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.onFailure(t);
                Log.d(TAG, "onFailure: "+t);
                view.hideLoading();
            }
        });
    }

}
