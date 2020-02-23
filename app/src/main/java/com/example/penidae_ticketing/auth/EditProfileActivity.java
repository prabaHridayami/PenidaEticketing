package com.example.penidae_ticketing.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.penidae_ticketing.Helper.PreferenceHelper;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditProfileActivity extends AppCompatActivity implements AuthView {

    EditText et_name, et_username, et_phone;
    Button btn_save;
    private AuthPresenter presenter;
    private PreferenceHelper preferenceHelper;
    Integer id_user;
    String name, username, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        init();

        presenter = new AuthPresenter(this, ApiClient.getService());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newname = et_name.getText().toString();
                String newuser = et_username.getText().toString();
                String newphone = et_phone.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                final String currentDateTime = sdf.format(new Date());

                presenter.edit(newname,newuser,currentDateTime,newphone,email,id_user);
            }
        });
    }

    private void init(){
        et_name = findViewById(R.id.et_fullname);
        et_username = findViewById(R.id.et_username);
        et_phone = findViewById(R.id.et_phone);
        btn_save = findViewById(R.id.btn_save);

        preferenceHelper=new PreferenceHelper(this);
        id_user = preferenceHelper.getId();
        name = preferenceHelper.getName();
        username = preferenceHelper.getUsername();
        phone = preferenceHelper.getPhone();
        email = preferenceHelper.getEmail();

        et_name.setText(name);
        et_username.setText(username);
        et_phone.setText(phone);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(User user) {
        preferenceHelper.setUser(user);
        Toast.makeText(this,"Edit Profile Success",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Username or Password invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Failed : "+t, Toast.LENGTH_SHORT).show();
    }
}
