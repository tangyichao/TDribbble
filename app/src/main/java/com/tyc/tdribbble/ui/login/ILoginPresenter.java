package com.tyc.tdribbble.ui.login;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface ILoginPresenter {
    public void loadToken(String code);
    public void loadUser(String token);
}
