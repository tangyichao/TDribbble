package com.tyc.tdribbble.ui.home;

import java.util.Map;

/**
 * 作者：tangyc on 2017/6/22
 * 邮箱：874500641@qq.com
 */
public class HomePresenter implements IHomePresenter {
    private HomeModel homeModel;
    public HomePresenter(IHomeView iHomeView){
        homeModel=new HomeModel(iHomeView);
    }
    @Override
    public void loadShots(Map<String, String> map, int type) {
        homeModel.loadShots(map, type);
    }

    @Override
    public void loadUser() {
        homeModel.loadUser();
    }
}
