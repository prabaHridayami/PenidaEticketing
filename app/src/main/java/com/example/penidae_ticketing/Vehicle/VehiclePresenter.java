package com.example.penidae_ticketing.Vehicle;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.VehicleOwner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehiclePresenter {
    private VehicleView view;
    private ApiService service;

    public VehiclePresenter(VehicleView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getOwner(){
        view.showLoading();
        service.allOwner().enqueue(new Callback<VehicleOwner>() {
            @Override
            public void onResponse(Call<VehicleOwner> call, Response<VehicleOwner> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getOwner());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<VehicleOwner> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }
}
