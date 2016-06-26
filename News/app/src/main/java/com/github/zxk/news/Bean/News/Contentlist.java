package com.github.zxk.news.Bean.News;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/16.
 */
public class Contentlist implements Serializable {

    /**
     * channelId : 5572a109b3cdc86cf39001db
     * channelName : 国内最新
     * desc : 引起社会的广泛关注和热烈讨论。
     * imageurls : []
     * link : http://news.xinhuanet.com/politics/2015-07/05/c_1115820327.htm
     * nid : 2678177830618688823
     * pubDate : 2015-07-06 16:30:00
     * source : 新华网
     * title : 新华视点:深改小组四份文件聚焦生态建设透露哪些新信号?
     */
    private static final long serialVersionUID = -7060210544600464481L;
    private String channelId;
    private String channelName;
    private String desc;
    private String link;
    private String nid;
    private String pubDate;
    private String source;
    private String title;
    private List<Imageurls> imageurls;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Imageurls> getImageurls() {
        return imageurls;
    }

    public void setImageurls(List<Imageurls> imageurls) {
        this.imageurls = imageurls;
    }
}
