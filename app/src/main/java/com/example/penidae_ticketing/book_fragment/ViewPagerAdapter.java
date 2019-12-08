package com.example.penidae_ticketing.book_fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return new BoatFragment();
        }else if (i == 1){
            return new HotelFragment();
        }else if (i == 2){
            return new VehicleFragment();
        }else if (i == 3){
            return new WatersportFragment();
        }else {
            return new TourFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    private FragmentManager getChildFragmentManager() {
        return null;
    }
}
