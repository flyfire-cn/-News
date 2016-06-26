package com.github.zxk.news.View;

import com.github.zxk.news.Bean.Joke.JokeText;
import com.github.zxk.news.Util.Constant;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 */
public interface JokeTextListView {
    /**
     * 获取数据 得到的数据填充到RecyclerView展示给用户
     *
     * @param jokeList
     */
    void refreshData(List<JokeText.ContentlistBean> jokeList);

    /**
     * 加载更多数据 得到的数据填充到RecyclerView展示给用户
     *
     * @param jokeList
     */
    void addMoreData(List<JokeText.ContentlistBean> jokeList);

    /**
     * 显示消息
     * 加载数据的过程中需要提示“正在加载”的反馈信息给用户
     *
     * @param info_type
     * @param infoText
     */
    void showInfo(Constant.INFO_TYPE info_type, String infoText);

    void hideInfo();
}
