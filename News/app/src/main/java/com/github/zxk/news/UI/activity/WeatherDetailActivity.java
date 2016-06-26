package com.github.zxk.news.UI.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.zxk.news.Bean.Weather;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.activity.base.BaseActivity;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 庄欣锴zz on 2016/6/5.
 */
public class WeatherDetailActivity extends BaseActivity {
    @Bind(R.id.tv_city)
    TextView mCity;
    @Bind(R.id.tv_update)
    TextView mUpdate;
    @Bind(R.id.tv_now_temp)
    TextView mTemp;
    @Bind(R.id.tv_now_weather)
    TextView tv_now_weather;
    @Bind(R.id.tv_aqi)
    TextView mAqi;
    @Bind(R.id.tv_quality)
    TextView mQuality;
    @Bind(R.id.tv_felt_air_temp)
    TextView mFelt;
    @Bind(R.id.tv_humidity)
    TextView mHum;
    @Bind(R.id.tv_wind)
    TextView mWind;
    @Bind(R.id.tv_uv_index)
    TextView mUv;
    @Bind(R.id.tv_dressing_index)
    TextView mDress;
    @Bind(R.id.tv_today_temp)
    TextView mTodayTemp;
    Weather mWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        //解析从HomeActivity传过来的JSON
        mWeather = new Gson().fromJson(getIntent().getStringExtra("data"), Weather.class);

        initView(mWeather);
    }

    private void initView(Weather mWeather) {
        mCity.setText(mWeather.getBasic().getCity());
        mUpdate.setText("更新时间:" + mWeather.getBasic().getUpdate().getLoc());
        mTodayTemp.setText(mWeather.getSuggestion().getComf().getTxt());
        mTemp.setText(mWeather.getNow().getTmp() + "°");
        mFelt.setText(mWeather.getNow().getFl() + "°");
        mHum.setText(mWeather.getNow().getHum() + "%");
        mWind.setText(mWeather.getNow().getWind().getDir() + " " + mWeather.getNow().getWind().getSc() + "级");
        mUv.setText(mWeather.getSuggestion().getUv().getTxt());
        mDress.setText(mWeather.getSuggestion().getDrsg().getTxt());
        mAqi.setText(mWeather.getAqi().getCity().getAqi());
        mQuality.setText(mWeather.getAqi().getCity().getQlty());

        //天气类型码
        int weatherCode = Integer.valueOf(mWeather.getNow().getCond().getCode());
        int[] weatherCodeArr = getResources().getIntArray(R.array.weather_code);
        tv_now_weather.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.weather_999, 0, 0, 0);
        TypedArray weatherIconArr = getResources().obtainTypedArray(R.array.weather_icon);
        int len = weatherIconArr.length();
        int[] weatherIconId = new int[len];
        for (int i = 0; i < len; i++) {
            weatherIconId[i] = weatherIconArr.getResourceId(i, 0);

        }
        weatherIconArr.recycle();
        //设置天气图标
        for (int i = 0; i < weatherCodeArr.length; i++) {
            if (weatherCodeArr[i] == weatherCode) {

                tv_now_weather.setCompoundDrawablesRelativeWithIntrinsicBounds(weatherIconId[i], 0, 0, 0);
            }
        }
        tv_now_weather.setText(mWeather.getNow().getCond().getTxt());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
