package com.example.booking_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdaptorPage extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> basliklar = new ArrayList<>();

    public void addFragment(Fragment fragment, String baslik){
        fragmentList.add(fragment);
        basliklar.add(baslik);
    }

    public AdaptorPage(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public CharSequence getPageTitle(int position) {
        return basliklar.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
