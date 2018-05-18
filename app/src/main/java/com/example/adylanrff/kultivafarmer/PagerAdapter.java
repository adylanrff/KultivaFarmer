package com.example.adylanrff.kultivafarmer;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numTabs;

    public PagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new TransaksiFragment();
            case 2:
                return new SaldoFragment();
            case 3:
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }

}
