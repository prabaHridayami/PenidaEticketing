package com.example.penidae_ticketing.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penidae_ticketing.Helper.PreferenceHelper;
import com.example.penidae_ticketing.MainActivity;
import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.User;

public class LoginActivity extends AppCompatActivity implements AuthView, View.OnClickListener {

    Button btn_login;
    TextView signup;
    EditText et_email, et_password;
    private PreferenceHelper preferenceHelper;
    private AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkLogin();
        init();
    }

    private void init(){
        btn_login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.create_account);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        btn_login.setOnClickListener(this);
        signup.setOnClickListener(this);
        presenter = new AuthPresenter(this, ApiClient.getService());
        preferenceHelper = new PreferenceHelper(this);
    }

    private void checkLogin(){
        preferenceHelper = new PreferenceHelper(this);
        boolean login = preferenceHelper.getLogin();
        if(login) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;

            case R.id.create_account:
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login(){
        String username = et_email.getText().toString();
        String password = et_password.getText().toString();

        if(validate(username,password)){
            presenter.login(username,password);
        }
    }

    private boolean validate(String username,String password){
        if (username.equals("")){
            et_email.setError("Field username tidak boleh kosong");
            return false;
        }

        if (password.equals("")){
            et_password.setError("Field password tidak boleh kosong");
            return false;
        }

        return true;
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(User user) {
        preferenceHelper.setUser(user);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
