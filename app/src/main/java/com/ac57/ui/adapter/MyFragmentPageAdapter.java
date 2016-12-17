package com.ac57.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Du_Li on 2016/12/15.
 * Desc:
 */

public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public void setmFragments(List<Fragment> mFragments) {
        this.mFragments = mFragments;
    }

    public MyFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments == null ? null : mFragments.get(position);
    }
}
