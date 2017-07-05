package com.tyc.tdribbble.ui.user.followers;

import com.tyc.tdribbble.ui.login.ILoginPresenter;
import com.tyc.tdribbble.ui.login.ILoginView;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class FollowersPresenter implements IFollowersPresenter {
    private IFollowersView iFollowersView;
    private FollowersModel model;

    public FollowersPresenter(IFollowersView iFollowersView) {
        this.iFollowersView = iFollowersView;
        model = new FollowersModel(iFollowersView);
    }

    @Override
    public void loadFollowers(String userId) {
        model.loadFollowers(userId);
    }

}
