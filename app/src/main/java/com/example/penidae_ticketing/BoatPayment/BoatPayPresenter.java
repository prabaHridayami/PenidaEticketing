package com.example.penidae_ticketing.BoatPayment;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Payment;
import com.example.penidae_ticketing.model.Room;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoatPayPresenter {
    private BoatPayView view;
    private ApiService service;

    public BoatPayPresenter(BoatPayView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void transBoat(Integer schedule, String depart_date, String reserve_date, Integer qty, Integer total_price, Integer id_user){
        view.showLoading();
        service.transBoat(depart_date, schedule, reserve_date, qty, total_price, id_user ).enqueue(new Callback<Payment>() {
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
