package com.tyc.tdribbble.ui.user.shots;

import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.entity.ShotsEntity;

import java.util.List;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IShotsView {
    public void showShots(List<ShotsEntity> shotsEntities);

    public void showError();
}
