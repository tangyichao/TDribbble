package com.tyc.tdribbble.ui.user.likes;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class LikesPresenter implements ILikesPresenter {
    private ILikesView iShotsView;
    private LikesModel model;

    public LikesPresenter(ILikesView iShotsView) {
        this.iShotsView = iShotsView;
        model = new LikesModel(iShotsView);
    }

    @Override
    public void loadShots(RxFragment rxFragment, String userId, int shotsType) {
        model.loadShots(rxFragment, userId, shotsType);
    }

}
