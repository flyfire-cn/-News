package com.github.zxk.news.UI.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zxk.news.Adapter.JokeImageListRecycleAdapter;
import com.github.zxk.news.Bean.Joke.JokeImage;
import com.github.zxk.news.Presenter.JokeImageListPresenter;
import com.github.zxk.news.Presenter.i.IContentListPresenter;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.fragment.base.BaseLazyFragment;
import com.github.zxk.news.Util.Constant;
import com.github.zxk.news.View.JokeImageListView;
import com.github.zxk.news.Widget.VaryViewWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 庄欣锴zz on 2016/6/22.
 * 笑话 图片类
 */
public class JokeImageListFragment extends BaseLazyFragment implements JokeImageListView, SwipeRefreshLayout.OnRefreshListener {
    View mView;
    @Bind(R.id.swipe_refresh_image_list)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycle_view_joke_image)
    RecyclerView mRecycleView;

    List<JokeImage.ContentlistBean> mContentlist;
    JokeImageListRecycleAdapter mRecycleAdapter;
    IContentListPresenter mJokeImagePresenter;
    VaryViewWidget mVaryViewWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_joke_image_list, null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();
        initView();
        mJokeImagePresenter = new JokeImageListPresenter(this, "1");
        mJokeImagePresenter.getFirstData();
    }

    /**
     * 初始化RecycleView
     */
    private void initView() {
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));

        mContentlist = new ArrayList<>();
        mRecycleAdapter = new JokeImageListRecycleAdapter(mContext, mContentlist);

        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mRecycleAdapter.getItemCount()) {
                    mJokeImagePresenter.getMoreData();
                }
            }
        });
        mRecycleView.setAdapter(mRecycleAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.red, R.color.yellow, R.color.black);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 得到更新数据
     */
    @Override
    public void onRefresh() {
        mJokeImagePresenter.getRefreshData();
    }

    /**
     * 刷新数据
     *
     * @param imageList
     */
    @Override
    public void refreshData(List<JokeImage.ContentlistBean> imageList) {
        mContentlist.clear();
        mContentlist.addAll(imageList);
        mRecycleAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 加载更多数据
     *
     * @param imageList
     */
    @Override
    public void addMoreData(List<JokeImage.ContentlistBean> imageList) {
        mContentlist.addAll(imageList);
        mRecycleAdapter.notifyDataSetChanged();
    }

    /**
     * 反馈信息
     *
     * @param info_type
     * @param infoText
     */
    @Override
    public void showInfo(Constant.INFO_TYPE info_type, String infoText) {
        if (mVaryViewWidget == null) {
            mVaryViewWidget = new VaryViewWidget(mSwipeRefreshLayout);
        }
        View infoView = null;
        switch (info_type) {
            case LOADING:
                infoView = LayoutInflater.from(mContext).inflate(R.layout.loading, null);
                break;
        }
        mVaryViewWidget.setLoadingView(infoView);
        mVaryViewWidget.showView(info_type);
    }

    @Override
    public void hideInfo() {
        if (mVaryViewWidget != null) {
            mVaryViewWidget.hideInfo();
        }
    }

}
