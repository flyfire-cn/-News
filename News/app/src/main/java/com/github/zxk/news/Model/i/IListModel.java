package com.github.zxk.news.Model.i;

/**
 * Created by 庄欣锴zz on 2016/6/17.
 * model  负责从服务器获取信息
 */
public interface IListModel {
    /**
     * 从服务器获取数据
     * 加载刷新数据
     */
    void loadRefreshData();

    /**
     * 加载更多数据
     */
    void loadMoreData();
}
