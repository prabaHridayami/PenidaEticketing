package com.example.penidae_ticketing.Watersport;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Attraction;
import com.example.penidae_ticketing.model.VehicleOwner;
import com.example.penidae_ticketing.model.Watersport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatersportPresenter {
    private WatersportView view;
    private ApiService service;

    public WatersportPresenter(WatersportView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

//    public void getAttraction(){
//        view.showLoading();
//        service.allatt().enqueue(new Callback<Attraction>() {
//            @Override
//            public void onResponse(Call<Attraction> call, Response<Attraction> response) {
//                if (response.isSuccessful()){
//                    view.onSuccess(response.body().getAttraction());
//                }
//                else {
//                    view.onError();
//                }
//                view.hideLoading();
//            }
//
//            @Override
//            public void onFailure(Call<Attraction> call, Throwable t) {
//                view.onFailure(t);
//            }
//        });
//    }

    public void getWatersport(){
        view.showLoading();
        service.allwatersport().enqueue(new Callback<Watersport>() {
            @Override
            public void onResponse(Call<Watersport> call, Response<Watersport> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getWatersport());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Watersport> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }
}
