package com.example.penidae_ticketing.Watersport;

import com.example.penidae_ticketing.model.AttractionItem;
import com.example.penidae_ticketing.model.OwnerItem;

import java.util.List;

public interface WatersportView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<AttractionItem> attractionItems);
    void onError();
    void onFailure(Throwable t);
}
