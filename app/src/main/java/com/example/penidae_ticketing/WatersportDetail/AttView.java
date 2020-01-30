package com.example.penidae_ticketing.WatersportDetail;

import com.example.penidae_ticketing.model.AttractionItem;
import com.example.penidae_ticketing.model.WatersportItem;

import java.util.List;

public interface AttView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<AttractionItem> attractionItems);
    void onError();
    void onFailure(Throwable t);
}
