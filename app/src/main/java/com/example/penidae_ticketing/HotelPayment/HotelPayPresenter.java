package com.example.penidae_ticketing.HotelPayment;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Payment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelPayPresenter {
    private HotelPayView view;
    private ApiService service;

    public HotelPayPresenter(HotelPayView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void transHotel(Integer id_room, String check_in, String check_out, String reserve_date, Integer total_price, Integer id_user){
        view.showLoading();
        service.transHotel(id_room, check_in, check_out, reserve_date, total_price, id_user ).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getMessage());
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
