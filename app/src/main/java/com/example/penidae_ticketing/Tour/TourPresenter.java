package com.example.penidae_ticketing.Tour;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Attraction;
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

    public void getTourPack(){
        view.showLoading();
        service.alltour().enqueue(new Callback<TourPack>() {
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
}
