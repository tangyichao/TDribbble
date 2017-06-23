package com.tyc.tdribbble.ui.user.followers;

import com.tyc.tdribbble.entity.UserEntity;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IFollowersView {
    public void showUser(UserEntity userEntity);

    public void showToken(String token);

    public void showError();
}
