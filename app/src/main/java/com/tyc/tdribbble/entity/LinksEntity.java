package com.tyc.tdribbble.entity;

import java.io.Serializable;

/**
 * 作者：tangyc on 2017/6/26
 * 邮箱：874500641@qq.com
 */
public class LinksEntity implements Serializable {
    /**
     * web : http://simplebits.com
     * twitter : https://twitter.com/simplebits
     */

    private String web;
    private String twitter;

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
