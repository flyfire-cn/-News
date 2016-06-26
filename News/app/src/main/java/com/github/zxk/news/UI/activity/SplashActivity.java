package com.github.zxk.news.UI.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.zxk.news.R;

import app.dinus.com.loadingdrawable.LoadingDrawable;
import app.dinus.com.loadingdrawable.render.scenery.DayNightLoadingRenderer;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 庄欣锴zz on 2016/6/20.
 */
public class SplashActivity extends Activity {
    @Bind(R.id.imageView_splash)
    ImageView mImageView;
    @Bind(R.id.tv_Version)
    TextView mTextView;
    private LoadingDrawable mLoadingDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setVersion();
        setLoadingImage();
        setStartActivity();

    }

    private void setLoadingImage() {
        mLoadingDrawable = new LoadingDrawable(new DayNightLoadingRenderer(this));
        mImageView.setImageDrawable(mLoadingDrawable);
        mLoadingDrawable.start();
    }

    /**
     * 延迟启动
     */
    private void setStartActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 4800);
    }

    private void setVersion() {
        mTextView.append(getAppVersionName(this));
    }

    /**
     * 获取当前版本号
     *
     * @param context
     * @return
     */
    public CharSequence getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
