package com.github.zxk.news.Model;

import com.github.zxk.news.Model.i.IListModel;
import com.github.zxk.news.Util.NetRequestUtil;
import com.github.zxk.news.Util.UriUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 */
public class JokeTextListModel implements IListModel {
    NetRequestUtil.RequestListener mRequestListener;
    int mCurrentPage = 1;
    Map<String, String> param = new HashMap<>();
    Map<String, String> headers = new HashMap<>();

    public JokeTextListModel(NetRequestUtil.RequestListener requestListener, String page) {
        mRequestListener = requestListener;
        param.put("page", page);
        headers.put("apikey", "0516233e5a0ff97dde693b97e868cee7");
    }

    /**
     * 加载刷新数据
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
        //获取JsonObject
        NetRequestUtil.getInstance().getJsonWithHeaders(UriUtil.URL_JOKE_Text, param, headers, mRequestListener);
    }
}
