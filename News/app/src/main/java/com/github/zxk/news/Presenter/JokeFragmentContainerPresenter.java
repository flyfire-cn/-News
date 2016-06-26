package com.github.zxk.news.Presenter;

import com.github.zxk.news.Model.JokeContainerFragmentModel;
import com.github.zxk.news.Model.i.IFragmentsContainerModel;
import com.github.zxk.news.Presenter.i.IFragmentsContainerPresenter;
import com.github.zxk.news.View.FragmentContainerView;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 */
public class JokeFragmentContainerPresenter implements IFragmentsContainerPresenter {
    FragmentContainerView mView;
    IFragmentsContainerModel mModel;

    public JokeFragmentContainerPresenter(FragmentContainerView view) {
        mView = view;
        mModel = new JokeContainerFragmentModel(this);
    }

    @Override
    public void setFragments() {
        mView.onSetFragment(mModel.getFragments(), mModel.getTitles());
    }
}
