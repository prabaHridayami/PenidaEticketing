package com.example.penidae_ticketing.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.User;

public class RegisterActivity extends AppCompatActivity implements AuthView {
    private static final String TAG = "Register";
    EditText et_fullname, et_username, et_phone, et_email, et_password, et_confirmpass;
    Button btn_signup;
    private AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_fullname = findViewById(R.id.et_fullname);
        et_username = findViewById(R.id.et_username);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_password= findViewById(R.id.et_password);
        et_confirmpass = findViewById(R.id.et_confirmpass);
        btn_signup = findViewById(R.id.btn_signup);
        presenter = new AuthPresenter(this, ApiClient.getService());

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_fullname.getText().toString();
                String username = et_username.getText().toString();
                String phone = et_phone.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String confirmpass = et_confirmpass.getText().toString();

                if (password.equals(confirmpass)){
                    Log.d(TAG, "onClick: register ");
                    presenter.register(name,username,email,password,phone);
//                    Toast.makeText(getApplicationContext(), name+username, Toast.LENGTH_SHORT).show();
                }else {
                    et_confirmpass.setError("Confirm Password incorrect");
                }
            }
        });
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
        Toast.makeText(this, "Register Succesfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(User user) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Failed : "+t, Toast.LENGTH_SHORT).show();
    }
}
