package com.example.penidae_ticketing.ReceiptActivity;

import com.example.penidae_ticketing.model.Upload;

import java.util.ArrayList;

public interface UploadView {
    void showLoading();
    void hideLoading();
    void onSuccess(Upload upload);
    void onError();
    void onFailure(Throwable t);
}
