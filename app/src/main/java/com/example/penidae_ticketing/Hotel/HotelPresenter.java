package com.example.penidae_ticketing.Hotel;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Hotel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelPresenter {
    private HotelView view;
    private ApiService service;

    public HotelPresenter(HotelView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getHotel(){
        view.showLoading();
        service.allhotel().enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getHotel(),response.body().getMessage());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    public void searchHotel(String check_in, String check_out, String guest, String room){
        view.showLoading();
        service.searchHotel(check_in,check_out,Integer.parseInt(guest),Integer.parseInt(room)).enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getHotel(),response.body().getMessage());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }
}
