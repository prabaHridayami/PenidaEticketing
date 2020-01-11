package com.example.penidae_ticketing.BoatDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.penidae_ticketing.R;

public class BoatDetailActivity extends AppCompatActivity {
    public static final String KEY_BOAT="boatItem";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_detail);
    }
}
