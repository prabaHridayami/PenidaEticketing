package com.example.penidae_ticketing.ReceiptActivity;

import android.app.DownloadManager;

import com.example.penidae_ticketing.api.ApiService;
import com.example.penidae_ticketing.model.Payment;
import com.example.penidae_ticketing.model.Room;
import com.example.penidae_ticketing.model.Upload;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPresenter {
    private UploadView view;
    private ApiService service;

    public UploadPresenter(UploadView view, ApiService service) {
        this.view = view;
        this.service = service;
    }

    public void uploadBoat(MultipartBody.Part image, RequestBody id_transaksi){
        view.showLoading();
        service.uploadBoat(image,id_transaksi).enqueue(new Callback<Upload>() {
            @Override
            public void onResponse(Call<Upload> call, Response<Upload> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Upload> call, Throwable t) {
                view.onFailure(t);

            }
        });
    }

    public void uploadHotel(MultipartBody.Part image, RequestBody id_transaksi){
        view.showLoading();
        service.uploadHotel(image,id_transaksi).enqueue(new Callback<Upload>() {
            @Override
            public void onResponse(Call<Upload> call, Response<Upload> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Upload> call, Throwable t) {
                view.onFailure(t);

            }
        });
    }

    public void uploadVehicle(MultipartBody.Part image, RequestBody id_transaksi){
        view.showLoading();
        service.uploadVehicle(image,id_transaksi).enqueue(new Callback<Upload>() {
            @Override
            public void onResponse(Call<Upload> call, Response<Upload> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Upload> call, Throwable t) {
                view.onFailure(t);

            }
        });
    }

    public void uploadWatersport(MultipartBody.Part image, RequestBody id_transaksi){
        view.showLoading();
        service.uploadWatersport(image,id_transaksi).enqueue(new Callback<Upload>() {
            @Override
            public void onResponse(Call<Upload> call, Response<Upload> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Upload> call, Throwable t) {
                view.onFailure(t);

            }
        });
    }

    public void uploadTour(MultipartBody.Part image, RequestBody id_transaksi){
        view.showLoading();
        service.uploadTour(image,id_transaksi).enqueue(new Callback<Upload>() {
            @Override
            public void onResponse(Call<Upload> call, Response<Upload> response) {
                if (response.isSuccessful()){
                    view.onSuccess(response.body());
                }
                else {
                    view.onError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<Upload> call, Throwable t) {
                view.onFailure(t);

            }
        });
    }


}
