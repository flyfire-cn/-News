package com.github.zxk.news;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by 庄欣锴zz on 2016/6/6.
 */
public class BaseAppActivity extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //生命周期的整个应用，应用摧毁，它才摧毁
        context = getApplicationContext();
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("LogUtils")
                .configShowBorders(true)
                .configLevel(LogLevel.TYPE_VERBOSE);

        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, "575537f1e0f55a75910008be", "StromZhuang"));
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static Context getContext() {
        return context;
    }
}