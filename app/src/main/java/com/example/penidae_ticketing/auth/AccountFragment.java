package com.example.penidae_ticketing.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penidae_ticketing.Helper.PreferenceHelper;
import com.example.penidae_ticketing.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {
    View v;
    Button btn_logout;
    TextView tv_name, tv_username, tv_email, tv_phone;
    ImageView iv_image, iv_profile;
    PreferenceHelper preferenceHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_account,container,false);

        btn_logout = v.findViewById(R.id.btn_logout);
        tv_name = v.findViewById(R.id.tv_name);
        tv_username = v.findViewById(R.id.tv_username);
        tv_email = v.findViewById(R.id.tv_email);
        tv_phone = v.findViewById(R.id.tv_phone);
        iv_image = v.findViewById(R.id.iv_image);
        iv_profile = v.findViewById(R.id.iv_profile);
        preferenceHelper = new PreferenceHelper(getActivity());

        tv_name.setText(preferenceHelper.getName());
        tv_username.setText(preferenceHelper.getUsername());
        tv_email.setText(preferenceHelper.getEmail());
        tv_phone.setText(preferenceHelper.getPhone());

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferenceHelper.setLogout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return v;


    }
}
