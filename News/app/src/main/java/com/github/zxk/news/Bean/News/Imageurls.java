package com.github.zxk.news.Bean.News;

import java.io.Serializable;

/**
 * Created by 庄欣锴zz on 2016/6/16.
 */
public class Imageurls implements Serializable {

    /**
     * height : 250
     * url : http://image20.it168.com/201604_800x800/2502/f4afc28a07a8a151.jpg
     * width : 546
     */

    private int height;
    private String url;
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
