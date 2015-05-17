package com.openatlas.homelauncher.fragment;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
/**
 * Created by BunnyBlue on 5/8/15.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
        {

            return new InstalledAppFragment();
        }else if (position==1){
            return  new DelayAppFragment();
        }else if (position==2){
            return  new StoredAppFragment();
        }
        return  null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}