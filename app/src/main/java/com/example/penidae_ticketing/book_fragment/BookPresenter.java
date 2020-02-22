package com.example.penidae_ticketing.book_fragment;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.TransBoat;
import com.example.penidae_ticketing.model.TransHotel;
import com.example.penidae_ticketing.model.TransTour;
import com.example.penidae_ticketing.model.TransVehicle;
import com.example.penidae_ticketing.model.TransWatersport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookPresenter {
    private BookView view;
    private ApiService service;

    public BookPresenter(BookView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getTransBoat(Integer id_user){
        view.showLoading();
        service.getTransBoat(id_user).enqueue(new Callback<TransBoat>() {
            @Override
            public void onResponse(Call<TransBoat> call, Response<TransBoat> response) {
                if (response.isSuccessful()){
                    view.onSuccessBoat(response.body().getTransBoat());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<TransBoat> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    public void getTransHotel(Integer id_user){
        view.showLoading();
        service.getTransHotel(id_user).enqueue(new Callback<TransHotel>() {
            @Override
            public void onResponse(Call<TransHotel> call, Response<TransHotel> response) {
                if (response.isSuccessful()){
                    view.onSuccessHotel(response.body().getTransHotel());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<TransHotel> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    public void getTransVehicle(Integer id_user){
        view.showLoading();
        service.getTransVehicle(id_user).enqueue(new Callback<TransVehicle>() {
            @Override
            public void onResponse(Call<TransVehicle> call, Response<TransVehicle> response) {
                if (response.isSuccessful()){
                    view.onSuccessVehicle(response.body().getTransRent());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<TransVehicle> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    public void getTransWatersport(Integer id_user){
        view.showLoading();
        service.getTransWatersport(id_user).enqueue(new Callback<TransWatersport>() {
            @Override
            public void onResponse(Call<TransWatersport> call, Response<TransWatersport> response) {
                if (response.isSuccessful()){
                    view.onSuccessWatersport(response.body().getTransWatersport());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<TransWatersport> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    public void getTransTour(Integer id_user){
        view.showLoading();
        service.getTransTour(id_user).enqueue(new Callback<TransTour>() {
            @Override
            public void onResponse(Call<TransTour> call, Response<TransTour> response) {
                if (response.isSuccessful()){
                    view.onSuccessTour(response.body().getTransTour());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<TransTour> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }



}
