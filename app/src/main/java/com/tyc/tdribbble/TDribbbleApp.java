package com.tyc.tdribbble;

import android.app.Application;

import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.utils.SpUtils;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：tangyc on 2017/6/20
 * 邮箱：874500641@qq.com
 */

public class TDribbbleApp extends Application {
    public static String TOKEN = "";
    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        TOKEN = SpUtils.getSpUtils(this).getString(ApiConstants.OAuth.TOKEN);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
