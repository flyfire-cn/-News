package com.github.zxk.news.UI.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zxk.news.Adapter.JokeTextListRecycleAdapter;
import com.github.zxk.news.Bean.Joke.JokeText;
import com.github.zxk.news.Presenter.JokeTextListPresenter;
import com.github.zxk.news.Presenter.i.IContentListPresenter;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.fragment.base.BaseLazyFragment;
import com.github.zxk.news.Util.Constant;
import com.github.zxk.news.View.JokeTextListView;
import com.github.zxk.news.Widget.VaryViewWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 * 笑话 文本类Fragments
 */
public class JokeTextListFragment extends BaseLazyFragment implements JokeTextListView, SwipeRefreshLayout.OnRefreshListener {
    View mView;
    @Bind(R.id.swipe_refresh_joke_list)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycle_view_joke_text)
    RecyclerView mRecyclerView;

    List<JokeText.ContentlistBean> mContentList;
    JokeTextListRecycleAdapter mRecycleAdapter;
    IContentListPresenter mJokeTextPresenter;
    VaryViewWidget mVaryViewWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_joke_text_list, null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 第一次Fragment 可见
     */
    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();
        initView();
        //获取数据
        mJokeTextPresenter = new JokeTextListPresenter(this, "1");
        //得到第一次加载的数据
        mJokeTextPresenter.getFirstData();
    }

    /**
     * 初始化RecycleView
     */
    private void initView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mContentList = new ArrayList<>();
        mRecycleAdapter = new JokeTextListRecycleAdapter(mContext, mContentList);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 正在滚动事件
             * @param recyclerView
             * @param dx
             * @param dy
             */
            private int lastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //当滚动收到列表最后一个Item的时候返回最后一个Item的position,
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }

            /**
             * 滚动状态改变
             * @param recyclerView
             * @param newState
             * SCROLL_STATE_IDLE  当前屏幕滚动时
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mRecycleAdapter.getItemCount()) {
                    mJokeTextPresenter.getMoreData();
                }
            }
        });
        mRecyclerView.setAdapter(mRecycleAdapter);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.red, R.color.yellow, R.color.black);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mJokeTextPresenter.getRefreshData();
    }

    /**
     * 刷新数据 展示
     *
     * @param jokeList
     */
    @Override
    public void refreshData(List<JokeText.ContentlistBean> jokeList) {
        mContentList.clear();
        mContentList.addAll(jokeList);
        //同步
        mRecycleAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 加载more 数据  展示
     *
     * @param jokeList
     */
    @Override
    public void addMoreData(List<JokeText.ContentlistBean> jokeList) {
        mContentList.addAll(jokeList);
        mRecycleAdapter.notifyDataSetChanged();
    }

    /**
     * 反馈信息
     *
     * @param info_type
     * @param infoText  提示文本内容
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
