package com.example.penidae_ticketing.Watersport;

import com.example.penidae_ticketing.model.AttractionItem;
import com.example.penidae_ticketing.model.OwnerItem;
import com.example.penidae_ticketing.model.WatersportItem;

import java.util.List;

public interface WatersportView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<WatersportItem> watersportItems);
    void onError();
    void onFailure(Throwable t);
}
