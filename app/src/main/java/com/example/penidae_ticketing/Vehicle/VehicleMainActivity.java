package com.example.penidae_ticketing.Vehicle;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.VehicleDetail.VehicleDetailActivity;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.OwnerItem;

import java.security.acl.Owner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class VehicleMainActivity extends AppCompatActivity implements VehicleView, View.OnClickListener, VehicleAdapter.OnClickListener {
    final Calendar myCalendar = Calendar.getInstance();
    private RecyclerView recycleVehicle;
    private VehicleAdapter adapter;
    private VehiclePresenter vehiclePresenter;
    List<OwnerItem> ownerItems;

    EditText et_pickup, et_guest;
    RadioGroup r_vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_main);
        getSupportActionBar().setElevation(0);

        recycleVehicle = findViewById(R.id.rv_vehicle);
        et_pickup = findViewById(R.id.et_pickup);
        et_guest =findViewById(R.id.et_guest);
        r_vehicle =findViewById(R.id.r_vehicle);

        et_pickup.setOnClickListener(this);
        et_guest.setOnClickListener(this);

        vehiclePresenter=new VehiclePresenter(this, ApiClient.getService());
        vehiclePresenter.getOwner();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_pickup:
                datePickerDialog();
                break;
            case R.id.et_guest:
                guestPickerDialog();
                break;
//            case R.id.et_room:
//                roomPickerDialog();
//                break;
//            case R.id.calendar:
//                datePickerDialog();
//                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(List<OwnerItem> ownerItems) {
        this.ownerItems=ownerItems;
        adapter=new VehicleAdapter(this,ownerItems);
        adapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recycleVehicle.setLayoutManager(layoutManager);
        recycleVehicle.setAdapter(adapter);
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

    private void datePickerDialog(){
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et_pickup.setText(sdf.format(myCalendar.getTime()));
            }
        };

        new DatePickerDialog(VehicleMainActivity.this, dateSetListener, myCalendar
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
                et_guest.setText(newVal+" guest(s)");
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

    @Override
    public void onClick(int position) {
        OwnerItem ownerItem=ownerItems.get(position);
        Bundle bundle=new Bundle();
        bundle.putParcelable(VehicleDetailActivity.KEY_VEHICLE,ownerItem);
        Intent intent=new Intent(this,VehicleDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
