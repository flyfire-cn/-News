package com.github.zxk.news.UI.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.github.zxk.news.Adapter.NewsListRecyclerAdapter;
import com.github.zxk.news.Bean.News.Contentlist;
import com.github.zxk.news.Presenter.NewsListPresenter;
import com.github.zxk.news.Presenter.i.IContentListPresenter;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.fragment.base.BaseLazyFragment;
import com.github.zxk.news.Util.Constant;
import com.github.zxk.news.View.NewsListView;
import com.github.zxk.news.Widget.VaryViewWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 庄欣锴zz on 2016/6/17.
 * 新闻资讯列表 List Fragment
 */
public class NewsListFragments extends BaseLazyFragment implements NewsListView, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.swipe_refresh_news_list)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycle_view_news)
    RecyclerView mRecyclerView;
    View mRootView;
    List<Contentlist> mNewsItemList;
    NewsListRecyclerAdapter mNewsListRecyclerAdapter;
    IContentListPresenter mNewsListPresenter;
    //新闻频道的ID
    private String mChannelId = null;
    VaryViewWidget mVaryViewWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_news_list, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    private void initViews() {
        mNewsItemList = new ArrayList<>();
        //固定大小
        mRecyclerView.setHasFixedSize(true);
        //layout 管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mNewsListRecyclerAdapter = new NewsListRecyclerAdapter(mContext, mNewsItemList);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mNewsListRecyclerAdapter.getItemCount()) {
                    //加载更多
                    LogUtils.d(getClass().getSimpleName(), "loading more data");
                    mNewsListPresenter.getMoreData();
                }
            }
        });
        mRecyclerView.setAdapter(mNewsListRecyclerAdapter);
        //Refresh 轮子颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.colorPrimary, R.color.yellow, R.color.black);
        mSwipeRefreshLayout.setOnRefreshListener(this);

    }

    /**
     * 设置新闻频道的唯一ID值
     *
     * @param channelId id值
     */
    public void setChannelId(String channelId) {
        mChannelId = channelId;
    }

    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();
        initViews();
        mNewsListPresenter = new NewsListPresenter(this, mChannelId);
        mNewsListPresenter.getFirstData();
    }

    @Override
    public void onRefresh() {
        mNewsListPresenter.getRefreshData();
    }

    /**
     * 刷新数据
     *
     * @param newsList newsList
     */
    @Override
    public void refreshData(List<Contentlist> newsList) {
        mNewsItemList.clear();
        mNewsItemList.addAll(newsList);
        mNewsListRecyclerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

    }

    /**
     * 上拉 添加数据
     *
     * @param newsList add newsList
     */
    @Override
    public void addMoreListData(List<Contentlist> newsList) {
        mNewsItemList.addAll(newsList);
        mNewsListRecyclerAdapter.notifyDataSetChanged();
    }

    /**
     * @param INFOType 枚举值 e.g. 没有网络、正在加载、异常
     * @param infoText infoText 提示的文本内容
     */
    @Override
    public void showInfo(Constant.INFO_TYPE INFOType, String infoText) {
        if (mVaryViewWidget == null) {
            mVaryViewWidget = new VaryViewWidget(mSwipeRefreshLayout);
        }
        View infoView = null;
        switch (INFOType) {
            case LOADING:
                infoView = LayoutInflater.from(mContext).inflate(R.layout.loading, null);
                break;
        }
        mVaryViewWidget.setLoadingView(infoView);
        mVaryViewWidget.showView(INFOType);
    }

    @Override
    public void hideInfo() {
        if (mVaryViewWidget != null) {
            mVaryViewWidget.hideInfo();
        }
    }

}