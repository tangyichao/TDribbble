package com.tyc.tdribbble.ui.login;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class LoginPresenter implements ILoginPresenter {
    private ILoginView iLoginView;
    private LoginModel model;
    public  LoginPresenter(ILoginView iLoginView)
    {
        this.iLoginView=iLoginView;
        model=new LoginModel(iLoginView);
    }

    @Override
    public void loadToken(RxAppCompatActivity rxAppCompatActivity,String code) {
        model.loadToken(rxAppCompatActivity,code);
    }

    @Override
    public void loadUser(RxAppCompatActivity rxAppCompatActivity) {
        model.loadUser(rxAppCompatActivity);
    }
}
