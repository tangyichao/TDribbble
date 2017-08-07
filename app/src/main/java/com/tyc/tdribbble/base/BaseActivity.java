package com.tyc.tdribbble.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.tyc.tdribbble.utils.SpUtils;
import com.tyc.tdribbble.utils.ThemeUtils;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 作者：tangyc on 2017/6/20
 * 邮箱：874500641@qq.com
 */


public abstract class BaseActivity extends RxAppCompatActivity  {

    private Unbinder bind;



    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        setContentView(layoutResID());
        EventBus.getDefault().register(this);
        bind = ButterKnife.bind(this);
        initData();

    }

    protected void initToolbar() {
    }
    protected abstract int layoutResID();

    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
//        if (SpUtils.getSpUtils(this).getBoolean("dark_theme")) {
//            ThemeUtils.notifyThemeApply(this);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Subscribe
    public void onMessageEvent(Object object) {
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
    }

    @Override
    @CallSuper
    protected void onStop() {
        super.onStop();
    }


}
