package com.example.penidae_ticketing.BoatDetail;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Schedule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchedulePresenter {
    private ScheduleView view;
    private ApiService service;

    public SchedulePresenter(ScheduleView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void getSchedule(Integer id_boat, String departure,Integer guest){
        view.showLoading();
        service.getSchedule(id_boat,departure,guest).enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body().getSchedule());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                view.onFailure(t);

            }
        });
    }

}
