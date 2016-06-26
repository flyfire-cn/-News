package com.github.zxk.news.Model;

import com.github.zxk.news.BaseAppActivity;
import com.github.zxk.news.Model.i.IFragmentsContainerModel;
import com.github.zxk.news.Presenter.i.IFragmentsContainerPresenter;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.fragment.NewsListFragments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/18.
 */
public class NewsContainerFragmentModel implements IFragmentsContainerModel {
    IFragmentsContainerPresenter mPresenter;
    List mFragments;
    String[] mTitles;

    public NewsContainerFragmentModel ( IFragmentsContainerPresenter presenter ) {
        mPresenter = presenter;
    }

    @Override
    public List getFragments ( ) {
        mFragments = new ArrayList ( );
        // 加入新闻频道ID
        String[] newsChannelId = BaseAppActivity.getContext ( ).getResources ( ).getStringArray ( R.array.news_channelId );
        for ( int i = 0 ; i < newsChannelId.length ; i++ ) {
            NewsListFragments newsListFragments = new NewsListFragments ( );
            newsListFragments.setChannelId ( newsChannelId[ i ] );
            mFragments.add ( newsListFragments );
        }
        return mFragments;
    }

    @Override
    public String[] getTitles ( ) {
        mTitles = BaseAppActivity.getContext ( ).getResources ( ).getStringArray ( R.array.news_titles );
        return mTitles;
    }
}