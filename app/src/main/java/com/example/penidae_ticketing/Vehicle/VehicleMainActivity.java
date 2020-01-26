package com.example.penidae_ticketing.Vehicle;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.OwnerItem;
import com.example.penidae_ticketing.model.VehicleItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class VehicleMainActivity extends AppCompatActivity implements VehicleView, View.OnClickListener, RentalListAdapter.OnClickListener {
    final Calendar myCalendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat;
    private RecyclerView recycleVehicle;
    private RentalListAdapter adapter;
    private VehiclePresenter vehiclePresenter;
    List<OwnerItem> ownerItems;

    EditText et_pickup, et_return;
    RadioGroup r_vehicle;
    RadioButton radioCar, radioBike;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_main);
        getSupportActionBar().setElevation(0);

        recycleVehicle = findViewById(R.id.rv_vehicle);
        et_pickup = findViewById(R.id.et_pickup);
        et_return =findViewById(R.id.et_return);
        r_vehicle =findViewById(R.id.r_vehicle);
        search = findViewById(R.id.search);
        radioBike = findViewById(R.id.radioBike);
        radioCar = findViewById(R.id.radioCar);

        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        et_pickup.setText(""+dateFormat.format(myCalendar.getTime()));
        myCalendar.add(Calendar.DAY_OF_MONTH,1);
        et_return.setText(""+dateFormat.format(myCalendar.getTime()));

        et_pickup.setOnClickListener(this);
        et_return.setOnClickListener(this);
        search.setOnClickListener(this);

        vehiclePresenter=new VehiclePresenter(this, ApiClient.getService());
        vehiclePresenter.getOwner();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_pickup:
                datePickerDialog();
                break;
            case R.id.et_return:
                guestPickerDialog();
                break;
            case R.id.search:
                String pickup = et_pickup.getText().toString();
                String re = et_return.getText().toString();
                Integer cat = r_vehicle.getCheckedRadioButtonId();
                String cate = "";
                if(cat == radioBike.getId()){
                     cate = "motor";
                }else if(cat == radioCar.getId()){
                    cate ="mobil";
                }else{
                    Toast.makeText(getApplicationContext(),"Select at least one category",Toast.LENGTH_LONG).show();
                }

                if(!pickup.isEmpty() && !re.isEmpty()&& !cate.equals("")){
                    Intent intent = new Intent(getApplicationContext(),ListRentalActivity.class);
                    intent.putExtra("pickup",pickup);
                    intent.putExtra("re",re);
                    intent.putExtra("cate",cate);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_LONG).show();
                }

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
        adapter=new RentalListAdapter(this,ownerItems);
        adapter.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recycleVehicle.setLayoutManager(layoutManager);
        recycleVehicle.setAdapter(adapter);
        Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessV(List<VehicleItem> vehicleItems) {

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
                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et_pickup.setText(sdf.format(myCalendar.getTime()));
            }
        };

        new DatePickerDialog(VehicleMainActivity.this, dateSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void guestPickerDialog(){
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                et_return.setText(sdf.format(myCalendar.getTime()));
            }
        };

        new DatePickerDialog(VehicleMainActivity.this, dateSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
