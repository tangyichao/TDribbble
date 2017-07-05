package com.tyc.tdribbble.ui.shotsdetails.introduction;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class ShotsIntroductionPresenter implements IShotsIntroductionPresenter {
    private IShotsIntroductionView iShotsView;
    private ShotsIntroductionModel model;

    public ShotsIntroductionPresenter(IShotsIntroductionView iShotsView) {
        this.iShotsView = iShotsView;
        model = new ShotsIntroductionModel(iShotsView);
    }

    @Override
    public void loadShotsIntroduction(String shotId) {
        model.loadShotsIntroduction(shotId);
    }

}
