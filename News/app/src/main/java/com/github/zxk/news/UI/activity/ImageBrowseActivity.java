package com.github.zxk.news.UI.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.github.zxk.news.R;
import com.github.zxk.news.UI.activity.base.BaseActivity;
import com.github.zxk.news.Util.ImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by 庄欣锴zz on 2016/6/22.
 */
public class ImageBrowseActivity extends BaseActivity {
    @Bind(R.id.pv_image)
    PhotoView mPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browse);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
        ImageUtil.displayImage(this, url, mPhotoView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
