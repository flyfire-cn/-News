package com.github.zxk.news.Model;

import com.github.zxk.news.Model.i.IChatModel;
import com.github.zxk.news.Presenter.i.IChatPresenter;
import com.github.zxk.news.Util.NetRequestUtil;
import com.github.zxk.news.Util.UriUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 庄欣锴zz on 2016/6/24.
 */
public class ChatModel implements IChatModel {
    NetRequestUtil.RequestListener mRequestListener;
    IChatPresenter mPresenter;
    Map<String, String> param = new HashMap<>();

    public ChatModel(IChatPresenter presenter, NetRequestUtil.RequestListener requestListener) {
        mPresenter = presenter;
        mRequestListener = requestListener;
        param.put("key", UriUtil.TURING_KEY);
    }

    /**
     * 发送请求消息
     *
     * @param message
     */
    @Override
    public void onRequestMessage(String message) {
        //请求内容
        param.put ( "info", EncodeString(message ));
        param.put ( "userid","12345678" );
        NetRequestUtil.getInstance ( ).getJson ( UriUtil.URL_CHAT, param, mRequestListener );
    }

    /**
     * 字符编码
     * @param message
     * @return
     */
    public  String EncodeString(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }

}
