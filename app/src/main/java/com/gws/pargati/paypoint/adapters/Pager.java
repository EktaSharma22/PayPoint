package com.gws.pargati.paypoint.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gws.pargati.paypoint.fragments.Intro1;
import com.gws.pargati.paypoint.fragments.Intro2;
import com.gws.pargati.paypoint.fragments.Intro3;

public class Pager extends FragmentPagerAdapter {

    public Pager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                Intro1 intro1 = new Intro1();
                return intro1;
            case 1:
                Intro2 intro2 = new Intro2();
                return intro2;
            case 2:
                Intro3 intro3 = new Intro3();
                return  intro3;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
