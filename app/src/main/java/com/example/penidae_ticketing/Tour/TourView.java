package com.example.penidae_ticketing.Tour;

import com.example.penidae_ticketing.model.AttractionItem;
import com.example.penidae_ticketing.model.TourPackageItem;

import java.util.List;

public interface TourView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<TourPackageItem> tourPackageItems);
    void onError();
    void onFailure(Throwable t);
}
