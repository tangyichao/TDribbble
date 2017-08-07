package com.tyc.tdribbble.ui.user.shots;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class ShotsPresenter implements IShotsPresenter {
    private IShotsView iShotsView;
    private ShotsModel model;

    public ShotsPresenter(IShotsView iShotsView) {
        this.iShotsView = iShotsView;
        model = new ShotsModel(iShotsView);
    }

    @Override
    public void loadShots(RxFragment rxFragment, String userId, int shotsType) {
        model.loadShots(rxFragment, userId, shotsType);
    }

}
