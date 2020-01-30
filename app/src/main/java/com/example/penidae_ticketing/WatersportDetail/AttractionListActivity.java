package com.example.penidae_ticketing.WatersportDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.AttractionItem;
import com.example.penidae_ticketing.model.Watersport;
import com.example.penidae_ticketing.model.WatersportItem;

import java.util.List;

public class AttractionListActivity extends AppCompatActivity implements AttView, AttAdapter.OnClickListener {
    public static final String KEY_ATT="watersportItem";
    WatersportItem watersportItem;
    List<AttractionItem> attractionItems;
    RecyclerView recyclerView;
    AttAdapter attAdapter;
    AttPresenter attPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);

        watersportItem=getIntent().getParcelableExtra(KEY_ATT);
        recyclerView = findViewById(R.id.rv_att);

        attPresenter = new AttPresenter(this, ApiClient.getService());
        attPresenter.getAttraction(watersportItem.getId());
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(List<AttractionItem> attractionItems) {
        this.attractionItems=attractionItems;
        if (!(attractionItems.isEmpty())){
            attAdapter=new AttAdapter(this,attractionItems);
            attAdapter.setOnClickListener(this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(attAdapter);
            Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No Available Ticket", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onError() {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onClick(int position) {

    }
}
