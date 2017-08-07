package com.tyc.tdribbble.ui.user.likes;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface ILikesModel {

    public void loadShots(RxFragment rxFragment, String userId, int shotsType);

}
