package com.github.zxk.news.Presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.zxk.news.Model.ChatModel;
import com.github.zxk.news.Model.i.IChatModel;
import com.github.zxk.news.Presenter.i.IChatPresenter;
import com.github.zxk.news.Util.NetRequestUtil;
import com.github.zxk.news.View.ChatView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 庄欣锴zz on 2016/6/24.
 */
public class ChatPresenter implements IChatPresenter, NetRequestUtil.RequestListener {
    IChatModel mModel;
    ChatView mView;

    public ChatPresenter(ChatView view) {
        mView = view;
        mModel = new ChatModel(this, this);
    }

    /**
     * 发送消息
     *
     * @param message
     */
    @Override
    public void onSendMessage(String message) {
        mModel.onRequestMessage(message);
    }

    /**
     * 请求响应
     *
     * @param response
     */
    @Override
    public void onResponse(JSONObject response) {
        LogUtils.i(response);
        try {
            if (response.getString("code").equals("100000")) {
                mView.onReceiveRespond(response.getString("text"));

            } else if (response.getString("code").equals("200000")) {

                mView.onReceiveRespond(response.getString("text"));
                mView.onReceiveRespond(response.getString("url"));

            } else if (response.getString("code").equals("302000")) {

                mView.onReceiveRespond(response.getString("text"));
                mView.onReceiveRespond(response.getString("list"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {

    }
}
