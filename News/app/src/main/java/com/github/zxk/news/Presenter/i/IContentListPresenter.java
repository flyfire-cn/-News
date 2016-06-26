package com.github.zxk.news.Presenter.i;

/**
 * Created by 庄欣锴zz on 2016/6/17.
 * 具备列表的Fragment
 * View 层需要调用Presenter层加载信息，所以Presenter层需要提供加载信息的接口
 */
public interface IContentListPresenter {
    /**
     * 得到更新的数据
     */
    void getRefreshData();
    /**
     *得到第一次加载数据
     */
    void getFirstData();

    /**
     * 得到更多数据
     */
    void getMoreData();
}
