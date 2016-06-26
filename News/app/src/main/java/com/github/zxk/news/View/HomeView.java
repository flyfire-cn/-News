package com.github.zxk.news.View;

import com.github.zxk.news.Bean.Weather;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/5.
 */
public interface HomeView {
    /**
     * 初始化Home的主界面的Viewpager
     *
     * @param fragments
     */
    void setFragmentPager(List fragments);

    /**
     * 初始化Home 的抽屉数据，如天气显示
     *
     * @param weather
     */
    void setNavigation(Weather weather);
}
