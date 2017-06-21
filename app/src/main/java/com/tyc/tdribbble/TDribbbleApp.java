package com.tyc.tdribbble;

import android.app.Application;

import com.tyc.tdribbble.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：tangyc on 2017/6/20
 * 邮箱：874500641@qq.com
 */

public class TDribbbleApp extends Application {
    public static String token="";
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        token= SpUtils.getSpUtils(this).getString("token");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
