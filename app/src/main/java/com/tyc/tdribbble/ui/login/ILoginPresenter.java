package com.tyc.tdribbble.ui.login;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface ILoginPresenter {
    public void loadToken(RxAppCompatActivity rxAppCompatActivity, String code);

    public void loadUser(RxAppCompatActivity rxAppCompatActivity);
}
