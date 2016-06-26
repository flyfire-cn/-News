package com.github.zxk.news.Model;

import com.github.zxk.news.Model.i.IHomeModel;
import com.github.zxk.news.Presenter.i.IHomePresenter;
import com.github.zxk.news.UI.fragment.ChatFragment;
import com.github.zxk.news.UI.fragment.JokeContentFragment;
import com.github.zxk.news.UI.fragment.NewsContentFragment;
import com.github.zxk.news.UI.fragment.base.BaseLazyFragment;
import com.github.zxk.news.Util.NetRequestUtil;
import com.github.zxk.news.Util.UriUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 庄欣锴zz on 2016/6/6.
 */
public class HomeModel implements IHomeModel {
    IHomePresenter mHomePresenter;
    List<BaseLazyFragment> mFragment;
    Map<String, String> param = new HashMap<>();
    Map<String, String> headers = new HashMap<>();

    public HomeModel(IHomePresenter homePresenter) {
        mHomePresenter = homePresenter;
        headers.put("apikey", "0516233e5a0ff97dde693b97e868cee7");

    }


    @Override
    public List getFragments() {
        mFragment = new ArrayList<>();
        mFragment.add(new NewsContentFragment());
        mFragment.add(new JokeContentFragment());
        mFragment.add(new ChatFragment());
        return mFragment;
    }

    @Override
    public void loadNavigation(final NetRequestUtil.RequestListener requestListener) {
        param.put("city", "广州");
        //获取天气
        NetRequestUtil.getInstance().getJsonWithHeaders(UriUtil.URL_WEATHER, param, headers, requestListener);
    }
}
