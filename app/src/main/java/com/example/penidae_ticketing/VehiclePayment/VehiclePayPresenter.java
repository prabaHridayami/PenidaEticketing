package com.example.penidae_ticketing.VehiclePayment;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Payment;
import com.example.penidae_ticketing.model.Room;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehiclePayPresenter {
    private VehiclePayView view;
    private ApiService service;

    public VehiclePayPresenter(VehiclePayView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void transVehicle(Integer id_user, String take, String re, Integer total_price, Integer id_vehicle, String transdate){
        view.showLoading();
        service.transVehicle(id_user, take, re, total_price, id_vehicle, transdate ).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getPayment());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Payment> call, Throwable t) {
                view.onFailure(t);
            }
        });


    }

}
