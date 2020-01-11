package com.example.penidae_ticketing.Hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.penidae_ticketing.Home.HotelAdapter;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.HotelItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HotelMainActivity extends AppCompatActivity implements View.OnClickListener, HotelView, HotelAdapter.OnClickListener {
    final Calendar myCalendar = Calendar.getInstance();
    private RecyclerView recyclerHotel;
    private HotelAdapter adapter;
    private HotelPresenter hotelPresenter;
    private SimpleDateFormat dateFormat;
    List<HotelItem> hotelItems;

    EditText et_checkin, et_checkout, et_guest, et_room;
    Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_main);
        getSupportActionBar().setElevation(0);

        recyclerHotel = findViewById(R.id.rv_boat);
        btn_search = findViewById(R.id.btn_search);
        et_checkin = findViewById(R.id.et_checkin);
        et_checkout = findViewById(R.id.et_checkout);
        et_guest =findViewById(R.id.et_guest);
        et_room =findViewById(R.id.et_room);

        dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        et_guest.setText("2");
        et_room.setText("2");
        et_checkin.setText(""+dateFormat.format(myCalendar.getTime()));
        myCalendar.add(Calendar.DAY_OF_MONTH,1);
        et_checkout.setText(""+dateFormat.format(myCalendar.getTime()));

        et_checkin.setOnClickListener(this);
        et_checkout.setOnClickListener(this);
        et_guest.setOnClickListener(this);
        et_room.setOnClickListener(this);
        btn_search.setOnClickListener(this);

        hotelPresenter=new HotelPresenter(this, ApiClient.getService());
        hotelPresenter.getHotel();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_checkout:
                durationPickerDialog();
                break;
            case R.id.et_guest:
                guestPickerDialog();
                break;
            case R.id.et_room:
                roomPickerDialog();
                break;
            case R.id.et_checkin:
                datePickerDialog();
                break;
            case R.id.btn_search:
                String check_in = et_checkin.getText().toString();
                String check_out = et_checkout.getText().toString();
                String guest = et_guest.getText().toString();
                String room = et_room.getText().toString();
                Toast.makeText(this,check_in+"",Toast.LENGTH_LONG).show();
                if (!check_in.isEmpty() && !check_out.isEmpty() ) {
                    Intent intent = new Intent(this, ListHotelActivity.class);
                    intent.putExtra("check_in", check_in);
                    intent.putExtra("check_out", check_out);
                    intent.putExtra("guest", guest);
                    intent.putExtra("room", room);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"please fill the check-in and check-out date",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void datePickerDialog(){
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et_checkin.setText(sdf.format(myCalendar.getTime()));
            }
        };

        new DatePickerDialog(HotelMainActivity.this, dateSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void durationPickerDialog(){
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et_checkout.setText(sdf.format(myCalendar.getTime()));
            }
        };

        new DatePickerDialog(HotelMainActivity.this, dateSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void guestPickerDialog(){
        NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(0);
        NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                et_guest.setText(newVal+"");
            }
        };

        numberPicker.setOnValueChangedListener(onValueChangeListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(numberPicker);
        builder.setTitle("Guests")
                .setIcon(R.drawable.ic_person_outline_black_24dp);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void roomPickerDialog(){
        NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(0);
        NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                et_room.setText(newVal+"");
            }
        };

        numberPicker.setOnValueChangedListener(onValueChangeListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(numberPicker);
        builder.setTitle("Rooms")
                .setIcon(R.drawable.ic_local_hotel_black_24dp);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(List<HotelItem> hotelItems) {
        this.hotelItems=hotelItems;
        adapter=new HotelAdapter(this,hotelItems);
        adapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerHotel.setLayoutManager(layoutManager);
        recyclerHotel.setAdapter(adapter);
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
        HotelItem hotelItem=hotelItems.get(position);
        Bundle bundle=new Bundle();
//        bundle.putParcelable(HotelDetailActivity.KEY_HOTEL,hotelItem);
        Intent intent=new Intent(this,HotelDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
