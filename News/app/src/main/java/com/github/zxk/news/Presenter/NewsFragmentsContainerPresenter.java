package com.github.zxk.news.Presenter;

import com.github.zxk.news.Model.NewsContainerFragmentModel;
import com.github.zxk.news.Model.i.IFragmentsContainerModel;
import com.github.zxk.news.Presenter.i.IFragmentsContainerPresenter;
import com.github.zxk.news.View.FragmentContainerView;

/**
 * Created by 庄欣锴zz on 2016/6/18.
 */
public class NewsFragmentsContainerPresenter implements IFragmentsContainerPresenter {
    FragmentContainerView mView;
    IFragmentsContainerModel mModel;

    public NewsFragmentsContainerPresenter(FragmentContainerView view) {
        mView = view;
        mModel = new NewsContainerFragmentModel(this);
    }

    @Override
    public void setFragments() {
        mView.onSetFragment(mModel.getFragments(), mModel.getTitles());
    }
}
