package com.tyc.tdribbble.ui.shotsdetails.introduction;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IShotsIntroductionPresenter {

    public void loadShotsIntroduction(RxFragment rxFragment, String shotId);
}
