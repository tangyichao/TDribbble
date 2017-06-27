package com.tyc.tdribbble.ui.home;

import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.UserEntity;

import java.util.List;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface  IHomeView {
    public void showShots(List<ShotsEntity> shotsEntities);
    public void loadMoreShots(List<ShotsEntity> shotsEntities);

    public void showUser(UserEntity userEntity);
    public void showError();
    public void showEmpty();
}
