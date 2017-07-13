package com.tyc.tdribbble.entity;

import java.util.List;

/**
 * 作者：tangyc on 2017/7/13
 * 邮箱：874500641@qq.com
 */
public class ShotsDetailsEntity {
    private List<CommentsEntity> commentsEntities;
    private TTEntity ttEntity;

    public List<CommentsEntity> getCommentsEntities() {
        return commentsEntities;
    }

    public void setCommentsEntities(List<CommentsEntity> commentsEntities) {
        this.commentsEntities = commentsEntities;
    }

    public TTEntity getTtEntity() {
        return ttEntity;
    }

    public void setTtEntity(TTEntity ttEntity) {
        this.ttEntity = ttEntity;
    }
}
