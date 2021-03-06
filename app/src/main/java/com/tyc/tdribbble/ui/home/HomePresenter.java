package com.tyc.tdribbble.ui.home;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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
    public void loadShots(RxAppCompatActivity rxAppCompatActivity, Map<String, String> map, int type) {
        homeModel.loadShots(rxAppCompatActivity,map, type);
    }

    @Override
    public void loadUser(RxAppCompatActivity rxAppCompatActivity) {
        homeModel.loadUser(rxAppCompatActivity);
    }
}
