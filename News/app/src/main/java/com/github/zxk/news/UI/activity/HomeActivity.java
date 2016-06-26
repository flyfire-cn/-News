package com.github.zxk.news.UI.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.zxk.news.Adapter.HomeFragmentPagerAdapter;
import com.github.zxk.news.Bean.Weather;
import com.github.zxk.news.Presenter.HomePresenter;
import com.github.zxk.news.Presenter.i.IHomePresenter;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.activity.base.BaseActivity;
import com.github.zxk.news.Util.Constant;
import com.github.zxk.news.Util.Theme;
import com.github.zxk.news.View.HomeView;
import com.github.zxk.news.Widget.MyViewPager;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView {
    @Bind(R.id.drawer_home)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.viewpager_home_fragments_container)
    MyViewPager mFragmentViewPager;
    @Bind(R.id.navigation_home)
    NavigationView mNavigationView;
    IHomePresenter mHomePresenter;
    ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_home);
        Theme.onActivityCreateSetTheme(this);
        ButterKnife.bind(this);
        initNavigation();
        addDrawerListener(mToolbar);
        initViews();
    }

    public void addDrawerListener(Toolbar toolbar) {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open,
                R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    private void initNavigation() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open,
                R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Menu menu = mNavigationView.getMenu();
                for (int i = 0; i < menu.size(); i++) {
                    if (item.getOrder() == i + 1) {
                        mFragmentViewPager.setCurrentItem(i, false);

                        item.setChecked(true);
                    } else {
                        item.setChecked(false);
                    }
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void initViews() {
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.getFragments();
        mHomePresenter.getNavigation();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
                switch (id) {
                    case R.id.action_theme_change_light:
                        Theme.changeToTheme(this, Constant.THEME_LIGHT);
                        break;
                    case R.id.action_theme_change_night:
                        Theme.changeToTheme(this, Constant.THEME_DARK);
                        break;
                    default:
                        break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setFragmentPager(List fragments) {
        mFragmentViewPager.setOffscreenPageLimit(fragments.size());
        mFragmentViewPager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(), fragments, this));
    }

    /**
     * Weather
     *
     * @param weather
     */
    @Override
    public void setNavigation(final Weather weather) {
        if (weather != null && weather.getNow() != null) {
            View headerView = mNavigationView.getHeaderView(0);
//                ImageView iconWeather = ( ( ImageView ) headerView.findViewById ( R.id.iv_weather_icon ) );
            //天气类型 - 晴、多云
            TextView tvTypeText = ((TextView) headerView.findViewById(R.id.tv_weather_txt));
            //当前气温 - 32 *C
            TextView tvTemperature = ((TextView) headerView.findViewById(R.id.tv_weather_temperature));
            //城市位置 - 广州
            TextView tvPosition = ((TextView) headerView.findViewById(R.id.tv_weather_position));
            //天气类型代码


            int weatherCode = Integer.valueOf(weather.getNow().getCond().getCode());
            int[] weatherCodeArr = getResources().getIntArray(R.array.weather_code);
            tvTypeText.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.weather_999, 0, 0);
            TypedArray ar = getResources().obtainTypedArray(R.array.weather_icon);
            int len = ar.length();
            int[] weatherDrawableID = new int[len];
            for (int i = 0; i < len; i++) {
                weatherDrawableID[i] = ar.getResourceId(i, 0);
            }
            ar.recycle();
            //设置天气类型图标
            for (int i = 0; i < weatherCodeArr.length; i++) {
                if (weatherCodeArr[i] == weatherCode) {
                    tvTypeText.setCompoundDrawablesWithIntrinsicBounds(0, weatherDrawableID[i], 0, 0);
                }
            }
            tvTypeText.setText(weather.getNow().getCond().getTxt());
            tvTemperature.setText(weather.getNow().getTmp());
            tvPosition.setText(weather.getBasic().getCity());

            headerView.findViewById(R.id.layout_drawer_header_weather).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, WeatherDetailActivity.class);
                    intent.putExtra("data", new Gson().toJson(weather));
                    startActivity(intent);
                }
            });
        }
    }
}
