package com.github.zxk.news.Bean.Joke;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 */
public class JokeText {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allPages":1488,"ret_code":0,"contentlist":[{"id":"5768ed276e360459041d67f6","text":"我和一朋友聊天讲笑话，她说，哎哟笑死我了，差点把下巴给我笑掉了，我说了句，没事，笑掉一个还有一个呢，瞬间被揍。","title":"下巴要笑掉了","type":1,"ct":"2016-06-21 15:30:47.602"}],"currentPage":1,"allNum":29759,"maxResult":20}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * allPages : 1488
     * ret_code : 0
     * contentlist : [{"id":"5768ed276e360459041d67f6","text":"我和一朋友聊天讲笑话，她说，哎哟笑死我了，差点把下巴给我笑掉了，我说了句，没事，笑掉一个还有一个呢，瞬间被揍。","title":"下巴要笑掉了","type":1,"ct":"2016-06-21 15:30:47.602"}]
     * currentPage : 1
     * allNum : 29759
     * maxResult : 20
     */

    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        private int allPages;
        private int ret_code;
        private int currentPage;
        private int allNum;
        private int maxResult;
        /**
         * id : 5768ed276e360459041d67f6
         * text : 我和一朋友聊天讲笑话，她说，哎哟笑死我了，差点把下巴给我笑掉了，我说了句，没事，笑掉一个还有一个呢，瞬间被揍。
         * title : 下巴要笑掉了
         * type : 1
         * ct : 2016-06-21 15:30:47.602
         */

        private List<ContentlistBean> contentlist;

        public int getAllPages() {
            return allPages;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getAllNum() {
            return allNum;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public List<ContentlistBean> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<ContentlistBean> contentlist) {
            this.contentlist = contentlist;
        }


    }

    public static class ContentlistBean {
        private String id;
        private String text;
        private String title;
        private int type;
        private String ct;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCt() {
            return ct;
        }

        public void setCt(String ct) {
            this.ct = ct;
        }
    }
}
