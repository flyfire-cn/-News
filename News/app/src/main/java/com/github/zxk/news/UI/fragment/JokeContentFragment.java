package com.github.zxk.news.UI.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.zxk.news.Adapter.JokeFragmentPagerAdapter;
import com.github.zxk.news.Presenter.JokeFragmentContainerPresenter;
import com.github.zxk.news.Presenter.i.IFragmentsContainerPresenter;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.fragment.base.BaseFragment;
import com.github.zxk.news.View.FragmentContainerView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by 庄欣锴zz on 2016/6/22.
 */
public class JokeContentFragment extends BaseFragment implements FragmentContainerView {
    @Bind(R.id.tab_Joke)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager_Joke)
    ViewPager mViewPager;

    FragmentPagerAdapter mAdapter;
    IFragmentsContainerPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_joke_container, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return false;
    }

    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();
        mToolbar.setTitle(R.string.Joke);
        mPresenter = new JokeFragmentContainerPresenter(this);
        mPresenter.setFragments();
    }

    @Override
    public void onSetFragment(List fragments, String[] titles) {
        mAdapter = new JokeFragmentPagerAdapter(getActivity().getSupportFragmentManager(), titles, fragments);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
