package com.github.zxk.news.View;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/18.
 * Fragments容器View ,为初始其子Fragments
 */
public interface FragmentContainerView {
    /**
     * 初始化  FragmentViewpager
     * @param fragments ViewPager数据源
     */
    void onSetFragment ( List fragments , String[] titles);
}
