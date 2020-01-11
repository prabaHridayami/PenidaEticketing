package com.example.penidae_ticketing.Room;

import com.example.penidae_ticketing.model.HotelItem;
import com.example.penidae_ticketing.model.RoomItem;

import java.util.List;

public interface RoomView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<RoomItem> roomItems);
    void onError();
    void onFailure(Throwable t);
}
