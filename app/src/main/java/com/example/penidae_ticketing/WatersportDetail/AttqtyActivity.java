package com.example.penidae_ticketing.WatersportDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.penidae_ticketing.Hotel.HotelMainActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.WatersportPayment.WatersportPaymentActivity;
import com.example.penidae_ticketing.model.AttractionItem;
import com.example.penidae_ticketing.model.WatersportItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AttqtyActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_WATERSPORT="watersportItem";
    public static final String KEY_ATT="attractionItem";

    WatersportItem watersportItem;
    AttractionItem attractionItem;

    TextView tv_number;
    ImageButton btn_plus, btn_minus;
    EditText playdate;
    Button payment;

    final Calendar myCalendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat;

    Integer number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attqty);

        watersportItem=getIntent().getParcelableExtra(KEY_WATERSPORT);
        attractionItem=getIntent().getParcelableExtra(KEY_ATT);

        tv_number = findViewById(R.id.tv_number);
        btn_plus = findViewById(R.id.plus);
        btn_minus = findViewById(R.id.minus);
        payment = findViewById(R.id.btn_payment);
        playdate = findViewById(R.id.playdate);

        tv_number.setText(Integer.toString(number));
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        playdate.setText(""+dateFormat.format(myCalendar.getTime()));

        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        payment.setOnClickListener(this);
        playdate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.plus:
                number +=1;
                tv_number.setText(Integer.toString(number));
                break;
            case R.id.minus:
                if (number>1){
                    number -=1;
                    tv_number.setText(Integer.toString(number));
                }
                break;
            case R.id.playdate:
                datePickerDialog();
                break;
            case R.id.btn_payment:
                Bundle bundle=new Bundle();
                bundle.putParcelable(WatersportPaymentActivity.KEY_ATT,attractionItem);
                Bundle watersport = new Bundle();
                watersport.putParcelable(WatersportPaymentActivity.KEY_WATERSPORT,watersportItem);
                Intent intent=new Intent(this,WatersportPaymentActivity.class);
                intent.putExtras(bundle);
                intent.putExtras(watersport);
                intent.putExtra("ticket",number);
                intent.putExtra("playdate",playdate.getText().toString());
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

                playdate.setText(sdf.format(myCalendar.getTime()));
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(AttqtyActivity.this, dateSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}
