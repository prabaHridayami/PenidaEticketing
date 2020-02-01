package com.example.penidae_ticketing.Tour;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Attraction;
import com.example.penidae_ticketing.model.Payment;
import com.example.penidae_ticketing.model.TourPack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourPresenter {
    private TourView view;
    private ApiService service;

    public TourPresenter(TourView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getTourPack(String tour_date, Integer guest){
        view.showLoading();
        service.alltour(tour_date,guest).enqueue(new Callback<TourPack>() {
            @Override
            public void onResponse(Call<TourPack> call, Response<TourPack> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getTourPackage());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<TourPack> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    public void transTour(String tour_date,Integer total_price, Integer id_user, Integer id_tour_package, Integer qty, String reserve_date){
        view.showLoading();
        service.transTour(tour_date,total_price,id_user,id_tour_package,qty,reserve_date).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if (response.isSuccessful()){
                    view.onSuccessPay(response.body().getPayment());
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
