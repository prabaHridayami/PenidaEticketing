package com.example.penidae_ticketing.VehicleDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.penidae_ticketing.R;

public class VehicleDetailActivity extends AppCompatActivity {
    public static final String KEY_VEHICLE="ownerItem";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);
    }
}
