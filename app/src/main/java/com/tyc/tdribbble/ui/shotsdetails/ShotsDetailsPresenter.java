package com.tyc.tdribbble.ui.shotsdetails;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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
    public void likeShot(RxAppCompatActivity rxAppCompatActivity,String shotId) {
        model.likeShot(rxAppCompatActivity,shotId);
    }

    @Override
    public void unlikeShot(RxAppCompatActivity rxAppCompatActivity,String shotId) {
        model.unlikeShot(rxAppCompatActivity,shotId);
    }

    @Override
    public void checklikeShot(RxAppCompatActivity rxAppCompatActivity,String shotId) {
        model.checklikeShot(rxAppCompatActivity,shotId);
    }

    @Override
    public void loadComments(RxAppCompatActivity rxAppCompatActivity, String shotId, HashMap<String, String> hashMap) {
        model.loadComments(rxAppCompatActivity,shotId,hashMap);
    }

    @Override
    public void likeComment(RxAppCompatActivity rxAppCompatActivity, String shotId, String commentId) {
        model.likeComment(rxAppCompatActivity,shotId,commentId);
    }

    @Override
    public void loadShotsIntroduction(RxAppCompatActivity rxAppCompatActivity, String shotId) {
        model.loadShotsIntroduction(rxAppCompatActivity,shotId);
    }

}
