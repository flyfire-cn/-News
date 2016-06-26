package com.github.zxk.news.Presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.zxk.news.Bean.Weather;
import com.github.zxk.news.Model.HomeModel;
import com.github.zxk.news.Model.i.IHomeModel;
import com.github.zxk.news.Presenter.i.IHomePresenter;
import com.github.zxk.news.Util.NetRequestUtil;
import com.github.zxk.news.View.HomeView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 庄欣锴zz on 2016/6/6.
 */
public class HomePresenter implements IHomePresenter {
    HomeView mHomeView;
    IHomeModel mHomeModel;
    Gson gson = new Gson();

    public HomePresenter(HomeView homeView) {
        mHomeView = homeView;
        mHomeModel = new HomeModel(this);
    }

    @Override
    public void getFragments() {
        mHomeView.setFragmentPager(mHomeModel.getFragments());
    }

    @Override
    public void getNavigation() {
        mHomeModel.loadNavigation(new NetRequestUtil.RequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                LogUtils.i(response);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONObject jsonWeather = jsonObject.getJSONArray("HeWeather data service 3.0").getJSONObject(0);
                    String s = jsonWeather.toString();
                    Weather weather = gson.fromJson(s, Weather.class);
                    mHomeView.setNavigation(weather);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }
}
