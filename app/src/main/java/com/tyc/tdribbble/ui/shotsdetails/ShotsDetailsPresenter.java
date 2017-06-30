package com.tyc.tdribbble.ui.shotsdetails;

import java.util.HashMap;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class ShotsDetailsPresenter implements IShotsDetailsPresenter {
    private IShotsDetailsView iShotsDetailsView;
    private ShotsDetailsModel model;

    public ShotsDetailsPresenter(IShotsDetailsView iShotsDetailsView) {
        this.iShotsDetailsView = iShotsDetailsView;
        model = new ShotsDetailsModel(iShotsDetailsView);
    }


    @Override
    public void likeShot(String shotId, String token) {
        model.likeShot(shotId, token);
    }

    @Override
    public void unlikeShot(String shotId, String token) {
        model.unlikeShot(shotId, token);
    }

    @Override
    public void checklikeShot(String shotId, String token) {
        model.checklikeShot(shotId, token);
    }

}
