package com.tyc.tdribbble;

import android.app.Application;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.utils.SpUtils;
import com.tyc.tdribbble.utils.TypefaceUtil;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import okhttp3.Cache;

/**
 * 作者：tangyc on 2017/6/20
 * 邮箱：874500641@qq.com
 */

public class TDribbbleApp extends Application {
    private static final long cacheSize = 1024 * 1024 * 20;//缓存文件最大限制大小20M
    public static Cache cache;
    public static String TOKEN = "";
    public static String avatar = "";
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);


        MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        TOKEN = SpUtils.getSpUtils(this).getString(ApiConstants.OAuth.TOKEN);
        File cacheFile = getCacheDir();  //设置缓存文件路径
        cache = new Cache(cacheFile, cacheSize);  //
        Logger.addLogAdapter(new AndroidLogAdapter());
        TypefaceUtil.setDefaultFont(this, "DEFAULT", "hobostd.otf");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
