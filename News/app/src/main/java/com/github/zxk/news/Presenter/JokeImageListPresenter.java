package com.github.zxk.news.Presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.zxk.news.Bean.Joke.JokeImage;
import com.github.zxk.news.Model.JokeImageListModel;
import com.github.zxk.news.Presenter.i.IContentListPresenter;
import com.github.zxk.news.Util.Constant;
import com.github.zxk.news.Util.NetRequestUtil;
import com.github.zxk.news.Util.NetUtil;
import com.github.zxk.news.View.JokeImageListView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 */
public class JokeImageListPresenter implements IContentListPresenter, NetRequestUtil.RequestListener {
    JokeImageListView mView;
    JokeImageListModel mModel;
    Gson mGson;

    public JokeImageListPresenter(JokeImageListView jokeImageListView, String page) {
        mGson = new Gson();
        mView = jokeImageListView;
        mModel = new JokeImageListModel(this, page);
    }

    @Override
    public void getRefreshData() {
        if (!NetUtil.isConnected()) {
            mView.showInfo(Constant.INFO_TYPE.NO_NET, null);
        } else {
            mView.showInfo(Constant.INFO_TYPE.LOADING, null);
            mModel.loadRefreshData();
        }
    }

    @Override
    public void getFirstData() {
        if (!NetUtil.isConnected()) {
            mView.showInfo(Constant.INFO_TYPE.NO_NET, null);
        } else {
            mView.showInfo(Constant.INFO_TYPE.LOADING, null);
            mModel.loadRefreshData();
        }
    }

    @Override
    public void getMoreData() {
        mModel.loadMoreData();
    }

    @Override
    public void onResponse(JSONObject response) {
        LogUtils.d(getClass().getSimpleName(), response);
        JokeImage jokeImage = mGson.fromJson(response.toString(), JokeImage.class);
        if (jokeImage.getShowapi_res_code() == 0 && jokeImage.getShowapi_res_body() != null) {
            List<JokeImage.ContentlistBean> contentlist = jokeImage.getShowapi_res_body().getContentlist();

            mView.hideInfo();
            if (jokeImage.getShowapi_res_body().getCurrentPage() == 1) {
                mView.refreshData(contentlist);
            } else {
                mView.addMoreData(contentlist);
            }
        }
    }

    @Override
    public void onError(VolleyError error) {

    }
}
