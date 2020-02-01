package com.example.penidae_ticketing.WatersportPayment;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Payment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatersportPayPresenter {
    private WatersportPayView view;
    private ApiService service;

    public WatersportPayPresenter(WatersportPayView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void transWatersport(Integer id_attraction, String reserve_date, Integer qty, String date, Integer total_price, Integer id_user){
        view.showLoading();
        service.transWatersport(id_attraction, reserve_date, qty, date, total_price, id_user ).enqueue(new Callback<Payment>() {
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
