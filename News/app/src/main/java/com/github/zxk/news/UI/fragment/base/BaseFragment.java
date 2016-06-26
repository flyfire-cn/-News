package com.github.zxk.news.UI.fragment.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.zxk.news.R;
import com.github.zxk.news.UI.activity.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 庄欣锴zz on 2016/6/17.
 */
public class BaseFragment extends BaseLazyFragment{
    @Bind ( R.id.toolbar )
    protected Toolbar mToolbar;

    @Override
    public void onViewCreated ( View view,  Bundle savedInstanceState ) {
        super.onViewCreated ( view, savedInstanceState );
        ButterKnife.bind ( this, view );
    }
    @Override
    public void onFirstUserVisible ( ) {
        super.onFirstUserVisible ( );
        setSupportActionBar ();

    }
    @Override
    public void onUserVisible ( ) {
        super.onUserVisible ( );
        setSupportActionBar ( );
    }

    /**
     * 设置toolbar
     */
    private void setSupportActionBar ( ) {
        ( ( AppCompatActivity ) getActivity ( ) ).setSupportActionBar ( mToolbar );
        ( ( HomeActivity ) getActivity ( ) ).addDrawerListener ( mToolbar );
    }
}
