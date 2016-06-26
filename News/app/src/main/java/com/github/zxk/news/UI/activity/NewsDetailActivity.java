package com.github.zxk.news.UI.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.zxk.news.Bean.News.Contentlist;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.activity.base.BaseActivity;
import com.github.zxk.news.Util.ImageUtil;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 庄欣锴zz on 2016/6/19.
 */
public class NewsDetailActivity extends BaseActivity {
    @Bind(R.id.image_news_details)
    ImageView mImageView;
    @Bind(R.id.progress_news_details)
    ProgressBar mProgressBar;
    @Bind(R.id.webView_news_details)
    WebView mWebView;
    Contentlist mContenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        setNavigationClick();
        initViews();
        setWebView();
    }


    /**
     * webView 设置
     * 动画设置
     */
    private void setWebView() {
        WebSettings setting = mWebView.getSettings();
        mWebView.setWebChromeClient(new MyWebChromeClient(mProgressBar));
        mWebView.setWebViewClient(new MyWebViewClient(mProgressBar));

        setting.setSupportZoom(true);//支持缩放
        setting.setBlockNetworkImage(true);//设置图片最后加载
    }

    /**
     * 获取上一个页面传过来的值
     */
    private void initViews() {
        //接受数据
        mContenList = (Contentlist) getIntent().getSerializableExtra("contentList");
        ImageUtil.displayImage(this, mContenList.getImageurls().get(0).getUrl(), mImageView);
        mWebView.loadUrl(mContenList.getLink());
        if (mToolbar != null) {
            mToolbar.setTitle(mContenList.getSource());
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    private void setNavigationClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPress();
            }
        });
    }


    private void onBackPress() {
        mWebView.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_share:
//                shareMsg(mContenList.getLink(), mContenList.getTitle(), "分享测试", mContenList.getImageurls().get(0).getUrl());
//                break;
//            case R.id.action_settings:
//                break;
//        }
//        return true;
//
//    }

    /**
     *
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_share) {
            shareMsg("分享", "标题", mContenList.getLink(), null);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * 分享
     *
     * @param activityTitle
     * @param msgTitle
     * @param msgText
     * @param imgPath
     */
    public void shareMsg(String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }

    /**
     * 渲染 解析网页
     * WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
     */
    private class MyWebChromeClient extends WebChromeClient {
        ProgressBar mProgressBar;

        public MyWebChromeClient(ProgressBar mProgressBar) {
            this.mProgressBar = mProgressBar;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    /**
     * 帮助Web处理各种通知 请求事件
     */
    private class MyWebViewClient extends WebViewClient {
        ProgressBar mProgressBar;

        public MyWebViewClient(ProgressBar mProgressBar) {
            this.mProgressBar = mProgressBar;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
