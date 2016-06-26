package com.github.zxk.news.View;

import com.github.zxk.news.Bean.Joke.JokeImage;
import com.github.zxk.news.Util.Constant;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 */
public interface JokeImageListView {
    /**
     * 更新数据
     *
     * @param imageList
     */
    void refreshData(List<JokeImage.ContentlistBean> imageList);

    /**
     * 加载更多数据
     *
     * @param imageList
     */
    void addMoreData(List<JokeImage.ContentlistBean> imageList);

    /**
     * 显示信息
     *
     * @param info_type
     * @param infoText
     */
    void showInfo(Constant.INFO_TYPE info_type, String infoText);

    void hideInfo();
}
