package com.tyc.tdribbble.entity;

/**
 * 作者：tangyc on 2017/6/29
 * 邮箱：874500641@qq.com
 */
public class Library {
    private String name;
    private String desc;
    private String url;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isCircleCrop() {
        return circleCrop;
    }

    public void setCircleCrop(boolean circleCrop) {
        this.circleCrop = circleCrop;
    }

    private boolean circleCrop;

    public Library(String name, String desc, String url, String imageUrl, boolean circleCrop) {
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.imageUrl = imageUrl;
        this.circleCrop = circleCrop;
    }
}
