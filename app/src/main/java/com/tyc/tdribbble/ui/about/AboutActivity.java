package com.tyc.tdribbble.ui.about;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.LibsAdapter;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.Library;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：tangyc on 2017/6/29
 * 邮箱：874500641@qq.com
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.rv_lib)
    RecyclerView mRvLib;
    static final Library[] libs = {
            new Library("Android support libraries",
                    "The Android support libraries offer a number of features that are not built into the framework.",
                    "https://developer.android.com/topic/libraries/support-library",
                    "https://avatars0.githubusercontent.com/u/1342004",
                    false),
            new Library("ButterKnife",
                    "Bind Android views and callbacks to fields and methods.",
                    "http://jakewharton.github.io/butterknife/",
                    "https://avatars.githubusercontent.com/u/66577",
                    true),
            new Library("Glide",
                    "An image loading and caching library for Android focused on smooth scrolling.",
                    "https://github.com/bumptech/glide",
                    "https://avatars.githubusercontent.com/u/423539",
                    false),
            new Library("RxJava",
                    "RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.",
                    "https://github.com/ReactiveX/RxJava",
                    "https://avatars1.githubusercontent.com/u/6407041",
                    false),
            new Library("OkHttp",
                    "An HTTP & HTTP/2 client for Android and Java applications.",
                    "http://square.github.io/okhttp/",
                    "https://avatars.githubusercontent.com/u/82592",
                    false),
            new Library("Retrofit",
                    "A type-safe HTTP client for Android and Java.",
                    "http://square.github.io/retrofit/",
                    "https://avatars.githubusercontent.com/u/82592",
                    false),
            new Library("gson",
                    "A Java serialization/deserialization library to convert Java Objects into JSON and back",
                    "https://github.com/google/gson",
                    "https://avatars0.githubusercontent.com/u/1342004",
                    false),
            new Library("CircleImageView",
                    "A circular ImageView for Android",
                    "https://github.com/hdodenhof/CircleImageView",
                    "https://avatars2.githubusercontent.com/u/1824223",
                    false),
            new Library("JSoup",
                    "Java HTML Parser, with best of DOM, CSS, and jquery.",
                    "https://github.com/jhy/jsoup/",
                    "https://avatars.githubusercontent.com/u/76934",
                    true),
            new Library("RxLifecycle",
                    "Lifecycle handling APIs for Android apps using RxJava",
                    "https://github.com/trello/RxLifecycle",
                    "https://avatars3.githubusercontent.com/u/6181431",
                    true),
            new Library("CarouselLayoutManager",
                    "Android Carousel LayoutManager for RecyclerView",
                    "https://github.com/Azoft/CarouselLayoutManager",
                    "https://avatars1.githubusercontent.com/u/6938975",
                    true)
    };
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.tv_cache)
    TextView mTvCache;

    @Override
    protected int layoutResID() {
        return R.layout.activity_about;
    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        final CarouselLayoutManager layoutCarouseManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutCarouseManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        mRvLib.setLayoutManager(layoutCarouseManager);
        mRvLib.setHasFixedSize(true);
        LibsAdapter libsAdapter = new LibsAdapter(this, libs);
        mRvLib.setAdapter(libsAdapter);
        mRvLib.addOnScrollListener(new CenterScrollListener());
        mTvVersion.setText("版本:" + getVersionName(this));
        mTvCache.setText("缓存大小");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    private String getCacheSize() {
        long fileSize = 0;
        File filesDir = getFilesDir();
        File cacheDir = getCacheDir();
        return null;
    }
}
