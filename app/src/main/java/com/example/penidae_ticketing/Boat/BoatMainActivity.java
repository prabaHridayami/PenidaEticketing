package com.example.penidae_ticketing.Boat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.penidae_ticketing.BoatDetail.BoatDetailActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.BoatItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BoatMainActivity extends AppCompatActivity implements BoatView, BoatAdapter.OnClickListener {
    private BoatPresenter boatPresenter;
    private BoatAdapter boatAdapter;
    final Calendar myCalendar = Calendar.getInstance();
    private RecyclerView recyclerBoat;
    EditText et_departure;
    Button btn_search;
    List<BoatItem> boatItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_main);
//        getSupportActionBar().setElevation(0);

        boatPresenter=new BoatPresenter(this, ApiClient.getService());
        boatPresenter.getBoat();

        recyclerBoat = findViewById(R.id.rv_boat);
//        et_departure = findViewById(R.id.et_departure);

//        et_departure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                datePickerDialog();
//            }
//        });


    }

//    private void datePickerDialog(){
//        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                myCalendar.set(Calendar.YEAR,year);
//                myCalendar.set(Calendar.MONTH,month);
//                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
//                String myFormat = "MM/dd/yy"; //In which you need put here
//                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//
//                et_departure.setText(sdf.format(myCalendar.getTime()));
//            }
//        };
//
//        new DatePickerDialog(BoatMainActivity.this, dateSetListener, myCalendar
//                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(List<BoatItem> boatItems) {
        this.boatItems=boatItems;
        boatAdapter=new BoatAdapter(this,boatItems);
        boatAdapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerBoat.setLayoutManager(layoutManager);
        recyclerBoat.setAdapter(boatAdapter);
        Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d(TAG, "onFailure: "+t);
        Toast.makeText(this, "Error : "+t, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(int position) {
        BoatItem boatItem=boatItems.get(position);
        Bundle bundle=new Bundle();
        bundle.putParcelable(BoatDetailActivity.KEY_BOAT,boatItem);
        Intent intent=new Intent(this, BoatDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
