package com.appswarrior.www.myapp.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.appswarrior.www.myapp.R;
import com.appswarrior.www.myapp.main.ContactScreen.ThirdFragment;
import com.appswarrior.www.myapp.main.CountriesScreen.SecondFragment;
import com.appswarrior.www.myapp.main.MapScreen.FirstFragment;

/**
 * Created by Eman Essam on 27/09/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FirstFragment();
        } else if (position == 1) {
            return new SecondFragment();
        } else {
            return new ThirdFragment();

        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.map_screen);
        } else if (position == 1) {
            return mContext.getString(R.string.country_screen);
        } else {
            return mContext.getString(R.string.contact_screen);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
