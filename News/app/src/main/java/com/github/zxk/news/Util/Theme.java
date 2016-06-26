package com.github.zxk.news.Util;

import android.app.Activity;
import android.content.Intent;

import com.github.zxk.news.R;

/**
 * Created by 庄欣锴zz on 2016/6/25.
 */
public class Theme {
    private static int sTheme;

    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /**
     * 切换主题
     *
     * @param activity
     */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            case Constant.THEME_LIGHT:
                activity.setTheme(R.style.AppTheme);
                break;
            case Constant.THEME_DARK:
                activity.setTheme(R.style.CustomThemeNight);
                break;
            default:
                break;
        }
    }
}
