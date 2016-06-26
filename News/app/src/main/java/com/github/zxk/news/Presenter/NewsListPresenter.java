package com.github.zxk.news.Presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.zxk.news.Bean.News.Contentlist;
import com.github.zxk.news.Bean.News.NewsItem;
import com.github.zxk.news.Bean.News.Pagebean;
import com.github.zxk.news.Model.NewsListModel;
import com.github.zxk.news.Presenter.i.IContentListPresenter;
import com.github.zxk.news.Util.Constant;
import com.github.zxk.news.Util.NetRequestUtil;
import com.github.zxk.news.Util.NetUtil;
import com.github.zxk.news.View.NewsListView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by 庄欣锴zz on 2016/6/17.
 */
public class NewsListPresenter implements IContentListPresenter, NetRequestUtil.RequestListener {
    NewsListView mNewsListView;
    NewsListModel mNewsListMode;
    Gson mGson;

    public NewsListPresenter(NewsListView newsListView, String channelId ) {
        mGson = new Gson ( );
        mNewsListView = newsListView;
        mNewsListMode = new NewsListModel ( this, channelId );
    }

    @Override
    public void getRefreshData ( ) {
        //无网刷新数据>底部显示SnackBar>点击打开设置界面
        if ( ! NetUtil.isConnected ( ) ) {
            mNewsListView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
        } else {
            mNewsListView.showInfo ( Constant.INFO_TYPE.LOADING,null);
            mNewsListMode.loadRefreshData ( );
        }
    }

    @Override
    public void getFirstData ( ) {
        if ( ! NetUtil.isConnected ( ) ) {
            mNewsListView.showInfo ( Constant.INFO_TYPE.NO_NET, null );
        } else {
            mNewsListView.showInfo ( Constant.INFO_TYPE.LOADING,null );
            mNewsListMode.loadRefreshData ( );
        }
    }

    @Override
    public void getMoreData ( ) {
        mNewsListMode.loadMoreData ( );
    }

    /**
     * 获取新闻数据
     * @param response
     */
    @Override
    public void onResponse ( JSONObject response ) {
        LogUtils.i ( response );
        NewsItem newsItemRoot = mGson.fromJson ( response.toString ( ), NewsItem.class );
        if ( newsItemRoot.getShowapi_res_code ( ) == 0 && newsItemRoot.getShowapi_res_body ( ) != null ) {
            Pagebean pagebean = newsItemRoot.getShowapi_res_body ( ).getPagebean ( );
            List< Contentlist > contentlist = pagebean.getContentlist ( );
            //没图你说个球呀
            ListIterator< Contentlist > iterator = contentlist.listIterator ( );
            while ( iterator.hasNext ( ) ) {
                //把没图的remove掉
                if ( iterator.next ( ).getImageurls ( ).size ( ) <= 0 ) {
                    iterator.remove ( );
                }
            }
            mNewsListView.hideInfo (  );
            if ( pagebean.getCurrentPage ( ) == 1 ) {
                mNewsListView.refreshData ( contentlist );
            }else{
                mNewsListView.addMoreListData ( contentlist );
            }
        }
    }

    @Override
    public void onError ( VolleyError error ) {

        LogUtils.i ( error );
    }
}
