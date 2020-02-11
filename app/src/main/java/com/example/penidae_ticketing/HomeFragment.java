package com.example.penidae_ticketing;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    ImageView imageRun;
    Handler handler;
    Runnable changeImage;

    private int[] images ={R.drawable.nuspen1,R.drawable.nuspen2,R.drawable.nuspen3,R.drawable.nuspen4,R.drawable.nuspen5};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imageRun = getView().findViewById(R.id.image_run);

        handler = new Handler();
        changeImage = new Runnable(){
            int flag = 0;
            @Override
            public void run() {
                imageRun.setImageResource(images[flag]);
                handler.postDelayed(changeImage, 3000);
                flag++;
                if (flag==(images.length)){
                    flag = 0;
                }
            }
        };
        changeImage.run();
    }
}