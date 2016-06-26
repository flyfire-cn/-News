package com.github.zxk.news.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/22.
 * 笑话
 */
public class JokeFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragment = null;
    private String[] mTitle = null;

    public JokeFragmentPagerAdapter(FragmentManager fm, String[] title, List fragments) {
        super(fm);
        mFragment = fragments;
        mTitle = title;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragment != null && position >= 0 && position < mFragment.size()) {
            return mFragment.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        if (mFragment != null) {
            return mFragment.size();
        } else {
            return 0;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
