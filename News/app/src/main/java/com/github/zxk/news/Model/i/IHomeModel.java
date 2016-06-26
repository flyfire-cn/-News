package com.github.zxk.news.Model.i;

import com.github.zxk.news.Util.NetRequestUtil;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/6.
 */
public interface IHomeModel {
    List getFragments();

    void loadNavigation(NetRequestUtil.RequestListener listener);
}
