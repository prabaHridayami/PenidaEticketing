package com.example.penidae_ticketing.Home;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Hotel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter   {
    private HomeView view;
    private ApiService service;

    public HomePresenter(HomeView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getHotel(){
        view.showLoading();
        service.allhotel().enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getHotel());
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

//    public void getHotel(){
//        view.showLoading();
//        service.allhotel().enqueue(new Callback<List<ListHotel>>() {
//            @Override
//            public void onResponse(Call<List<ListHotel>> call, Response<List<ListHotel>> response) {
//                if (response.isSuccessful()){
//                    view.onSuccess(response.body());
//                }
//                else {
//                    view.onError();
//                }
//                view.hideLoading();
//            }
//
//            @Override
//            public void onFailure(Call<List<ListHotel>> call, Throwable t) {
//                view.onFailure(t);
//            }
//        });
//    }
}
