package com.example.penidae_ticketing.Watersport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.WatersportDetail.AttractionListActivity;
import com.example.penidae_ticketing.model.WatersportItem;

public class WatersportDetailActivity extends AppCompatActivity {
    public static final String KEY_ATT="watersportItem";
    WatersportItem watersportItem;

    ImageView iv_watersport;
    TextView tv_title, tv_address, tv_desc, tv_valid;
    Button selectAtt;

    String title, address, desc, valid, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watersport_detail);

        init();
        setText();

        selectAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(AttractionListActivity.KEY_ATT,watersportItem);
                Intent intent = new Intent(getApplicationContext(), AttractionListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void init(){
        watersportItem=getIntent().getParcelableExtra(KEY_ATT);

        iv_watersport = findViewById(R.id.iv_watersport);
        tv_title = findViewById(R.id.tv_title);
        tv_address = findViewById(R.id.tv_address);
        tv_desc = findViewById(R.id.tv_desc);
        tv_valid = findViewById(R.id.tv_valid);
        selectAtt = findViewById(R.id.selectAtt);

        title = watersportItem.getName();
        address = watersportItem.getAddress();
        image = watersportItem.getImage();
    }

    private void setText(){
        Glide.with(this).load(image).centerCrop().placeholder(R.drawable.nuspen1).into(iv_watersport);
        tv_title.setText(title);
        tv_address.setText(address);
    }
}
