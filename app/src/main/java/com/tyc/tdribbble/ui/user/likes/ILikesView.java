package com.tyc.tdribbble.ui.user.likes;

import com.tyc.tdribbble.entity.LikesEntity;
import com.tyc.tdribbble.entity.ShotsEntity;

import java.util.List;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface ILikesView {
    public void showLikes(List<LikesEntity> likesEntities);

    public void showError();
}
