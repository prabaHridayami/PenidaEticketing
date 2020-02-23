package com.example.penidae_ticketing.Vehicle;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Vehicle;
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

    public void getRental(String pick_up, String re, String cat){
        view.showLoading();
        service.searchRental(pick_up,re,cat).enqueue(new Callback<VehicleOwner>() {
            @Override
            public void onResponse(Call<VehicleOwner> call, Response<VehicleOwner> response) {
                if (response.isSuccessful()){
                    if (response.body().getMessage().equals("Successfull")) {
                        view.onSuccess(response.body().getOwner());
                    }else{
                        view.onSuccessNull(response.body().getMessage());
                    }
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

    public void getVehicle(Integer id, String pickup_date, String return_date){
        view.showLoading();
        service.searchVehicle(id,pickup_date,return_date).enqueue(new Callback<Vehicle>() {
            @Override
            public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                if (response.isSuccessful()){
                    view.onSuccessV(response.body().getVehicle());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Vehicle> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }
}
