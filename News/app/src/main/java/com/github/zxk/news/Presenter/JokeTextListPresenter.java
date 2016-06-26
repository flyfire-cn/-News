package com.github.zxk.news.Presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.zxk.news.Bean.Joke.JokeText;
import com.github.zxk.news.Model.JokeTextListModel;
import com.github.zxk.news.Presenter.i.IContentListPresenter;
import com.github.zxk.news.Util.Constant;
import com.github.zxk.news.Util.NetRequestUtil;
import com.github.zxk.news.Util.NetUtil;
import com.github.zxk.news.View.JokeTextListView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 * 提供加载的数据接口
 */
public class JokeTextListPresenter implements IContentListPresenter, NetRequestUtil.RequestListener {
    JokeTextListView mJokeTextListView;
    JokeTextListModel mJokeTextListModel;
    Gson mGson;

    public JokeTextListPresenter(JokeTextListView jokeTextListView, String page) {
        mGson = new Gson();
        mJokeTextListView = jokeTextListView;
        mJokeTextListModel = new JokeTextListModel(this, page);

    }

    /**
     * 加载更新数据
     * 判断是网络连接
     */
    @Override
    public void getRefreshData() {
        if (!NetUtil.isConnected()) {
            mJokeTextListView.showInfo(Constant.INFO_TYPE.NO_NET, null);
        } else {
            mJokeTextListView.showInfo(Constant.INFO_TYPE.LOADING, null);
            mJokeTextListModel.loadRefreshData();
        }
    }

    /**
     * 第一次加载数据
     */
    @Override
    public void getFirstData() {
        if (!NetUtil.isConnected()) {
            mJokeTextListView.showInfo(Constant.INFO_TYPE.NO_NET, null);
        } else {
            mJokeTextListView.showInfo(Constant.INFO_TYPE.LOADING, null);
            mJokeTextListModel.loadRefreshData();
        }
    }

    /**
     * 加载更多数据
     */
    @Override
    public void getMoreData() {
        mJokeTextListModel.loadMoreData();
    }

    /**
     * Json请求响应
     *
     * @param response
     */
    @Override
    public void onResponse(JSONObject response) {
        LogUtils.d(getClass().getSimpleName(), response);
        //gson获取的数据存入到 实体类中
        JokeText joketext = mGson.fromJson(response.toString(), JokeText.class);
        if (joketext.getShowapi_res_code() == 0 && joketext.getShowapi_res_body() != null) {
            List<JokeText.ContentlistBean> contentlist = joketext.getShowapi_res_body().getContentlist();
            //将文本的<p></p>替换掉
            for (int i = 0; i < contentlist.size(); i++) {
                filterP(contentlist.get(i));
            }
            mJokeTextListView.hideInfo();
            if (joketext.getShowapi_res_body().getCurrentPage() == 1) {
                mJokeTextListView.refreshData(contentlist);
            } else {
                mJokeTextListView.addMoreData(contentlist);
            }
        }

    }

    private void filterP(JokeText.ContentlistBean contentlistBean) {
        String string = contentlistBean.getText();
        String regex = "</?p>";
        string = string.replaceAll(regex, "\n");
        contentlistBean.setText(string);
    }

    @Override
    public void onError(VolleyError error) {

    }
}
