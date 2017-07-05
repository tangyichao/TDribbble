package com.tyc.tdribbble.ui.login;

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
    public void loadToken(String code) {
        model.loadToken(code);
    }

    @Override
    public void loadUser() {
        model.loadUser();
    }
}
