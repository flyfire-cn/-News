package com.github.zxk.news.Util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.github.zxk.news.BaseAppActivity;

/**
 * Created by 庄欣锴zz on 2016/6/17.
 * 网络相关工具类 轮子
 */
public class NetUtil {
    private NetUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     *
     * @return
     */
    public static boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) BaseAppActivity.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否wifi连接
     *
     * @return
     */
    public static boolean isWifi() {
        ConnectivityManager connectivity = (ConnectivityManager) BaseAppActivity.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        }
        return connectivity.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }
    /**
     * 打开网络设置界面
     */

}
