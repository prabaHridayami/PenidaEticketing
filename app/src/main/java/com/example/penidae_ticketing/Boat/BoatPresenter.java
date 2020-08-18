package com.example.penidae_ticketing.Boat;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Boat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoatPresenter {
    private BoatView view;
    private ApiService service;

    public BoatPresenter(BoatView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getBoat(){
        view.showLoading();
        service.allboat().enqueue(new Callback<Boat>() {
            @Override
            public void onResponse(Call<Boat> call, Response<Boat> response) {
                if (response.isSuccessful()){
                    if(response.body().getMessage()=="Successfull") {
                        view.onSuccess(response.body().getBoat());
                    }else{
                        view.onEmpty("Empty");
                    }
                }else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Boat> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }
}
