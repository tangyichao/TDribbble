package com.tyc.tdribbble.ui.user.followers;

import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.entity.UserEntity;

import java.util.List;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IFollowersView {
    public void showFollowers(List<FollowersEntity> followersEntities);

    public void showError();
}
