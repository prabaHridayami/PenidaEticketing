package com.example.penidae_ticketing.BoatDetail;

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
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penidae_ticketing.Boat.BoatMainActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.model.BoatItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BoatDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_BOAT="boatItem";

    final Calendar myCalendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat;
    ImageView iv_boat;
    EditText et_departure, et_guest;
    Button btn_search;
    TextView tv_title, tv_desc;
    String departure, guest, title, desc;
    BoatItem boatItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_detail);

        init();
        setText();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departure = et_departure.getText().toString();
                guest = et_guest.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ScheduleActivity.KEY_BOAT,boatItem);
                intent.putExtra("departure", departure);
                intent.putExtra("guest", guest);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void init(){
        et_departure = findViewById(R.id.et_departure);
        et_guest = findViewById(R.id.et_guest);
        btn_search = findViewById(R.id.btn_search);
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_desc);
        iv_boat =findViewById(R.id.iv_boat);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        et_departure.setText(""+dateFormat.format(myCalendar.getTime()));
        et_guest.setText("1");

        et_departure.setOnClickListener(this);
        et_guest.setOnClickListener(this);
        btn_search.setOnClickListener(this);
    }

    private void setText(){
        boatItem=getIntent().getParcelableExtra(KEY_BOAT);

        tv_title.setText(boatItem.getName());
        tv_desc.setText(boatItem.getDesc());
        Glide.with(this).load(boatItem.getImage()).centerCrop().placeholder(R.drawable.nuspen1).into(iv_boat);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_departure:
                datePickerDialog();
                break;
            case R.id.et_guest:
                guestPickerDialog();
                break;
            case R.id.btn_search:
                datePickerDialog();
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

                et_departure.setText(sdf.format(myCalendar.getTime()));
            }
        };

        DatePickerDialog  datePickerDialog = new DatePickerDialog(BoatDetailActivity.this, dateSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
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
