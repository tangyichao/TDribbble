package com.tyc.tdribbble.ui.shotsdetails.introduction;

import com.tyc.tdribbble.entity.ShotsEntity;

import java.util.List;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IShotsIntroductionView {
    public void showShotsIntroduction(ShotsEntity shotsEntity);

    public void showError();
}
