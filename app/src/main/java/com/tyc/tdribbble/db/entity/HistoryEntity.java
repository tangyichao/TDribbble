package com.tyc.tdribbble.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：tangyc on 2017/8/8
 * 邮箱：874500641@qq.com
 */
@Entity
public class HistoryEntity {
    @Id
    private Long id;
    @Property(nameInDb = "TIME")
    private Long time;
    @Property(nameInDb = "NAME")
    private String name;

    @Generated(hash = 656320324)
    public HistoryEntity(Long id, Long time, String name) {
        this.id = id;
        this.time = time;
        this.name = name;
    }

    @Generated(hash = 1235354573)
    public HistoryEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
