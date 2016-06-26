package com.github.zxk.news.Bean.News;

/**
 * Created by 庄欣锴zz on 2016/6/16.
 */
public class Showapi_res_body {

    private Pagebean pagebean;
    /**
     * pagebean : {}
     * ret_code : 0
     */

    private int ret_code;

    public Pagebean getPagebean() {
        return pagebean;
    }

    public void setPagebean(Pagebean pagebean) {
        this.pagebean = pagebean;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public static class PagebeanBean {
    }
}
