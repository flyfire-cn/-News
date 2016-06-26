package com.github.zxk.news.Util;

/**
 * Created by 庄欣锴zz on 2016/6/17.
 * 全局常量
 */
public class Constant {
    /**
     * 显示提示信息 没网络，警告，加载中，空，加载异常
     */
    public enum INFO_TYPE {
        NO_NET, ALERT, LOADING, EMPTY, ORIGIN
    }

    /**
     * 白色主题（默认）
     */
    public static final int THEME_LIGHT = 0;
    /**
     * 深色主题
     */
    public static final int THEME_DARK = 1;

    //存储
    public static final String THEME = "theme";
    public static final String THEME_NAME = "theme_name";
}
