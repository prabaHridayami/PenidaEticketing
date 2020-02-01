package com.example.penidae_ticketing.Tour;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.penidae_ticketing.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TourMainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_tourdate, et_guest;
    Button search;
    String tourdate,guest;

    final Calendar myCalendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_main);
        getSupportActionBar().setElevation(0);

        et_tourdate = findViewById(R.id.et_tourdate);
        et_guest = findViewById(R.id.et_guest);
        search =findViewById(R.id.search);

        dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        et_guest.setText("2");
        et_tourdate.setText(""+dateFormat.format(myCalendar.getTime()));

        tourdate = et_tourdate.getText().toString();
        guest = et_guest.getText().toString();

        et_tourdate.setOnClickListener(this);
        et_guest.setOnClickListener(this);
        search.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_tourdate:
                datePickerDialog();
                break;
            case R.id.et_guest:
                guestPickerDialog();
                break;
            case R.id.search:
                Intent intent = new Intent(this, TourListActivity.class);
                intent.putExtra("tourdate", tourdate);
                intent.putExtra("guest", guest);
                startActivity(intent);
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
                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et_tourdate.setText(sdf.format(myCalendar.getTime()));
            }
        };

        new DatePickerDialog(TourMainActivity.this, dateSetListener, myCalendar
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
}
