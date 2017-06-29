package com.tyc.tdribbble.ui.about;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.LibsAdapter;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.Library;

import butterknife.BindView;
import github.hellocsl.layoutmanager.gallery.GalleryLayoutManager;

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
                    true)
    };


    @Override
    protected int layoutResID() {
        return R.layout.activity_about;
    }

    @Override
    protected void initData() {

        GalleryLayoutManager layoutGalleryManager = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
        layoutGalleryManager.attach(mRvLib, 30);
        mRvLib.setAdapter(new LibsAdapter(this, libs));
        layoutGalleryManager.setItemTransformer(new ScaleTransformer());
    }

    private class ScaleTransformer implements GalleryLayoutManager.ItemTransformer {
        @Override
        public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
            item.setPivotX(item.getWidth() / 2.0f);
            item.setPivotY(item.getHeight() / 2.0f);
            float scale = 1 - 0.3f * Math.abs(fraction);
            item.setScaleX(scale);
            item.setScaleY(scale);
        }
    }
}
