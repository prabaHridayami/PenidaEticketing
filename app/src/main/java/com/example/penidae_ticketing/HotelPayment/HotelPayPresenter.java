package com.example.penidae_ticketing.HotelPayment;

import android.widget.Toast;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Payment;
import com.example.penidae_ticketing.model.Room;
import com.example.penidae_ticketing.model.RoomItem;

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

    public String transHotel(Integer id_room, String check_in, String check_out, String reserve_date, Integer total_price, Integer id_user){
        final String[] message = new String[1];
        view.showLoading();
        service.transHotel(id_room, check_in, check_out, reserve_date, total_price, id_user ).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(Call<Payment> call, Response<Payment> response) {
                if (response.isSuccessful()){
                    message[0] = response.body().getMessage();
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

        return message[0];
    }

    public void getRoomId(String check_in, String check_out,Integer id_cat){
        view.showLoading();
        service.getRoomId(check_in,check_out,id_cat).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()){
                    view.onSuccessId(response.body().getId_room());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                view.onFailure(t);

            }
        });
    }


}
