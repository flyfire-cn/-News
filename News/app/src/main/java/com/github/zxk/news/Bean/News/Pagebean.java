package com.github.zxk.news.Bean.News;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/16.
 */
public class Pagebean {

    /**
     * allNum : 54188
     * allPages : 2710
     * contentlist : []
     * currentPage : 1//当前页面
     * maxResult : 20
     */

    private int allNum;
    private int allPages;
    private int currentPage;
    private int maxResult;
    private List<Contentlist> contentlist;

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public List<Contentlist> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<Contentlist> contentlist) {
        this.contentlist = contentlist;
    }
}
