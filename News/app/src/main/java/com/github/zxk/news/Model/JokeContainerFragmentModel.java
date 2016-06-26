package com.github.zxk.news.Model;

import com.github.zxk.news.BaseAppActivity;
import com.github.zxk.news.Model.i.IFragmentsContainerModel;
import com.github.zxk.news.Presenter.i.IFragmentsContainerPresenter;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.fragment.JokeImageListFragment;
import com.github.zxk.news.UI.fragment.JokeTextListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 */
public class JokeContainerFragmentModel implements IFragmentsContainerModel {
    IFragmentsContainerPresenter mPresenter;
    List mFragment;
    String[] mTitle;

    public JokeContainerFragmentModel(IFragmentsContainerPresenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 将Fragment 加入到List
     *
     * @return
     */
    @Override
    public List getFragments() {
        mFragment = new ArrayList();
        mFragment.add(new JokeTextListFragment());
        mFragment.add(new JokeImageListFragment());
        return mFragment;
    }

    @Override
    public String[] getTitles() {
        mTitle = BaseAppActivity.getContext ( ).getResources ( ).getStringArray ( R.array.joke_titles );
        return mTitle;
    }
}
