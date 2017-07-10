package com.tyc.tdribbble.ui.bigimage;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.ShotsEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 作者：tangyc on 2017/6/28
 * 邮箱：874500641@qq.com
 */
public class BigImageActivity extends BaseActivity {
    @BindView(R.id.iv_big_image)
    ImageView mIvBigImage;
    @BindView(R.id.iv_bg_image)
    ImageView mIvBgImage;

    @Override
    protected int layoutResID() {
        return R.layout.acvitity_big_image;
    }

    @Override
    protected void initData() {
        ShotsEntity shots = (ShotsEntity) getIntent().getSerializableExtra(ApiConstants.SHOTS);
        if (shots.isAnimated()) {
            Glide.with(this).load(shots.getImages().getHidpi()).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvBigImage);
            Glide.with(this).load(shots.getImages().getHidpi()).bitmapTransform(new BlurTransformation(this, 18, 3)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvBgImage);
        } else {
            Glide.with(this).load(shots.getImages().getNormal()).into(mIvBigImage);
            Glide.with(this).load(shots.getImages().getNormal()).bitmapTransform(new BlurTransformation(this, 18, 3)).into(mIvBgImage);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
