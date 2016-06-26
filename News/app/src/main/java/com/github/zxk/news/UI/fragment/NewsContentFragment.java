package com.github.zxk.news.UI.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zxk.news.Adapter.NewsFragmentPagerAdapter;
import com.github.zxk.news.Presenter.NewsFragmentsContainerPresenter;
import com.github.zxk.news.Presenter.i.IFragmentsContainerPresenter;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.fragment.base.BaseFragment;
import com.github.zxk.news.View.FragmentContainerView;

import java.io.File;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 庄欣锴zz on 2016/6/17.
 * 容器
 */
public class NewsContentFragment extends BaseFragment implements FragmentContainerView {
    @Bind ( R.id.tab_news )
    TabLayout mTabLayout;
    @Bind ( R.id.viewpager_news )
    ViewPager mViewPager;
    FragmentPagerAdapter mFragmentsPagerAdapter;
    IFragmentsContainerPresenter mPresenter;


    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setHasOptionsMenu ( true );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        super.onCreateView ( inflater, container, savedInstanceState );
        View view = inflater.inflate ( R.layout.fragment_news_contanier, null );
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                shareMsg("分享", "标题", "内容", null);
            }
        });
        return view;
    }


    @Override
    public void onFirstUserVisible ( ) {
        super.onFirstUserVisible ();
        mToolbar.setTitle ( R.string.News );
        mPresenter = new NewsFragmentsContainerPresenter ( this );
        mPresenter.setFragments ( );
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_share:
//                shareMsg("分享", "标题", "内容", null);
//                break;
//        }
//        return false;
//    }

    @Override
    public void onSetFragment ( List fragments, String[] titles ) {
        mFragmentsPagerAdapter = new NewsFragmentPagerAdapter ( getActivity ( ).getSupportFragmentManager ( ), titles, fragments );
        mViewPager.setOffscreenPageLimit ( fragments.size ( ) );
        mViewPager.setAdapter ( mFragmentsPagerAdapter );

        mViewPager.addOnPageChangeListener ( new ViewPager.OnPageChangeListener ( ) {
            @Override
            public void onPageScrolled ( int position, float positionOffset, int positionOffsetPixels ) {

            }

            @Override
            public void onPageSelected ( int position ) {

            }

            @Override
            public void onPageScrollStateChanged ( int state ) {

            }
        } );
        mTabLayout.setupWithViewPager ( mViewPager );

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

}
