package com.example.penidae_ticketing.BoatDetail;

import com.example.penidae_ticketing.model.RoomItem;
import com.example.penidae_ticketing.model.ScheduleItem;

import java.util.List;

public interface ScheduleView {
    void showLoading();
    void hideLoading();
    void onSuccess(List<ScheduleItem> scheduleItems);
    void onError();
    void onFailure(Throwable t);
}
