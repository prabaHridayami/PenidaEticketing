package com.example.penidae_ticketing.WatersportDetail;

import com.example.penidae_ticketing.Watersport.WatersportView;
import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Attraction;
import com.example.penidae_ticketing.model.Watersport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttPresenter {
    private AttView view;
    private ApiService service;

    public AttPresenter(AttView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getAttraction(Integer id){
        view.showLoading();
        service.allatt(id).enqueue(new Callback<Attraction>() {
            @Override
            public void onResponse(Call<Attraction> call, Response<Attraction> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getAttraction());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Attraction> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }


}
