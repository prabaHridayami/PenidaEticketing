package com.example.penidae_ticketing.Room;

import android.widget.Toast;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Hotel;
import com.example.penidae_ticketing.model.Room;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomPresenter {
    private RoomView view;
    private ApiService service;

    public RoomPresenter(RoomView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getRoom(String checkIn, String checkOut, Integer id, Integer room){
        view.showLoading();
        service.getRoom(checkIn,checkOut,room,id).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getRoom());
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
