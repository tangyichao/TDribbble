package com.tyc.tdribbble.ui.user.followers;

import com.tyc.tdribbble.ui.login.ILoginPresenter;
import com.tyc.tdribbble.ui.login.ILoginView;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class FollowersPresenter implements ILoginPresenter {
    private ILoginView iLoginView;
    private FollowersModel model;

    public FollowersPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        model = new FollowersModel(iLoginView);
    }

    @Override
    public void loadToken(String code) {
        model.loadToken(code);
    }

    @Override
    public void loadUser(String token) {
        model.loadUser(token);
    }
}
