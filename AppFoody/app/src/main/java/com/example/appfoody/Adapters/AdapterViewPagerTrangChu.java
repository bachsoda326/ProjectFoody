package com.example.appfoody.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.appfoody.View.Fragments.AngiFragment;
import com.example.appfoody.View.Fragments.OdauFragment;

public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {

    OdauFragment odauFragment;
    AngiFragment angiFragment;
    public AdapterViewPagerTrangChu(FragmentManager fm) {
        super(fm);
        odauFragment = new OdauFragment();
        angiFragment = new AngiFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return odauFragment;
            case 1:
                return angiFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
