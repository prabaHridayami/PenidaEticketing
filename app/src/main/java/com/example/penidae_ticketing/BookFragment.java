package com.example.penidae_ticketing;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.penidae_ticketing.book_fragment.BoatFragment;
import com.example.penidae_ticketing.book_fragment.HotelFragment;
import com.example.penidae_ticketing.book_fragment.TourFragment;
import com.example.penidae_ticketing.book_fragment.VehicleFragment;
import com.example.penidae_ticketing.book_fragment.ViewPagerAdapter;
import com.example.penidae_ticketing.book_fragment.WatersportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class BookFragment extends Fragment {

    View v;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private int[] navLabel = {
            R.string.boat,
            R.string.hotel,
            R.string.vehicle,
            R.string.watersport,
            R.string.tour,

    };

    private int[] navIcon = {
            R.drawable.boat,
            R.drawable.hotel,
            R.drawable.vehicle,
            R.drawable.watersport,
            R.drawable.temple,

    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_book, container, false);

        viewPager = v.findViewById(R.id.viewPager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), getContext());
        viewPager.setAdapter(adapter);
        tabLayout = v.findViewById(R.id.tabLayout_id);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcon(tabLayout);

        return v;
    }

    private void createTabIcon(TabLayout tabLayout) {
        Log.d("countTAB", "createTabIcon: "+tabLayout.getTabCount());
        for (int i = 0; i < tabLayout.getTabCount(); i++){
            LinearLayout tab = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.costum_tablayout, null);

            TextView tab_label = tab.findViewById(R.id.nav_label);
            ImageView tab_icon =  tab.findViewById(R.id.nav_icon);

            tab_label.setText(getResources().getString(navLabel[i]));
            tab_label.setText(navLabel[i]);
            tab_label.setTextColor(getResources().getColor(R.color.white));
            tab_icon.setImageResource(navIcon[i]);

            tabLayout.getTabAt(i).setCustomView(tab);
//            tabLayout.getTabAt(i).getIcon().setColorFilter(Color.parseColor("#000"), PorterDuff.Mode.SRC_IN);
        }
    }

}
