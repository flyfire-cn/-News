package com.github.zxk.news.Model;

import com.github.zxk.news.Model.i.IListModel;
import com.github.zxk.news.Util.NetRequestUtil;
import com.github.zxk.news.Util.UriUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 庄欣锴zz on 2016/6/17.
 */
public class NewsListModel implements IListModel {
    NetRequestUtil.RequestListener mRequestListener;
    int mCurrentPage = 1;
    Map<String, String> param = new HashMap<>();
    Map<String, String> headers = new HashMap<>();

    public NewsListModel(NetRequestUtil.RequestListener requestListener, String channelId) {
        mRequestListener = requestListener;
        param.put("channelId", channelId);
        headers.put("apikey", "0516233e5a0ff97dde693b97e868cee7");
    }

    /**
     * 加载更新数据
     */
    @Override
    public void loadRefreshData() {
        mCurrentPage = 1;
        loadMoreData();
    }

    /**
     * 加载更多数据
     */
    @Override
    public void loadMoreData() {
        param.put("page", "" + mCurrentPage++);
        NetRequestUtil.getInstance().getJsonWithHeaders(UriUtil.URL_NEWS, param, headers, mRequestListener);
    }
}
