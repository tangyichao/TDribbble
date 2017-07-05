package com.tyc.tdribbble.ui.shotsdetails;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.adapter.TFragmentPageAdapter;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.TTEntity;
import com.tyc.tdribbble.ui.bigimage.BigImageActivity;
import com.tyc.tdribbble.ui.shotsdetails.Comments.CommentsFragment;
import com.tyc.tdribbble.ui.shotsdetails.introduction.ShotsIntroductionFragment;
import com.tyc.tdribbble.utils.ScreenUtils;
import com.tyc.tdribbble.view.widget.BadgedFourThreeImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class ShotsDetailsActivity extends BaseActivity implements IShotsDetailsView {
    @BindView(R.id.iv_shots)
    BadgedFourThreeImageView mIvShots;

    @BindView(R.id.tl_user_shots)
    TabLayout mTlUserShots;
    @BindView(R.id.vp_user_shots)
    ViewPager mVpUserShots;
    @BindView(R.id.fab_favorite)
    FloatingActionButton mFabFavorite;
    @BindView(R.id.cl)
    CoordinatorLayout cl;
    private List<Fragment> list = new ArrayList<>();
    private String[] tabStrs = {"简介", "评论"};
    ShotsEntity shots;
    private ShotsDetailsPresenter presenter;
    @Override
    protected int layoutResID() {
        return R.layout.activity_shots_details;
    }

    @Override
    protected void initData() {
        final int width = ScreenUtils.getScreenWidth(this);
        ViewGroup.LayoutParams params = mIvShots.getLayoutParams();
        params.width = width;
        params.height = width * 3 / 4;
        mIvShots.setLayoutParams(params);
        shots = (ShotsEntity) getIntent().getSerializableExtra("shots");
        boolean isAnimated = shots.isAnimated();
        if (isAnimated) {
            String hidpi = shots.getImages().getHidpi();
            Glide.with(ShotsDetailsActivity.this).load(hidpi).asGif().placeholder(R.drawable.bg_linear_shots).diskCacheStrategy(DiskCacheStrategy.SOURCE).dontTransform().fitCenter().into(mIvShots);
        } else {
            String normal = shots.getImages().getNormal();
            Glide.with(ShotsDetailsActivity.this).load(normal).placeholder(R.drawable.bg_linear_shots).override(width, width * 3 / 4).into(mIvShots);
        }
        list.add(ShotsIntroductionFragment.newInstance(shots));
        list.add(CommentsFragment.newInstance(String.valueOf(shots.getId())));
        mTlUserShots.setupWithViewPager(mVpUserShots);
        FragmentManager fm = getSupportFragmentManager();
        TFragmentPageAdapter adapter = new TFragmentPageAdapter(fm, tabStrs, list);
        mVpUserShots.setAdapter(adapter);
        presenter = new ShotsDetailsPresenter(this);
        presenter.checklikeShot(String.valueOf(shots.getId()));
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_shots, R.id.fab_favorite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_shots: {
                Intent intent = new Intent();
                intent.setClass(this, BigImageActivity.class);
                intent.putExtra("shots", shots);
                if (shots.isAnimated()) {
                    startActivity(intent);
                } else {
                    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, mIvShots, getResources().getString(R.string.str_shots_tran)).toBundle());
                }
                break;
            }
            case R.id.fab_favorite:
                presenter.likeShot(String.valueOf(shots.getId()));
                break;
        }
    }


    @Override
    public void likeShot(TTEntity ttEntity) {
        mFabFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
    }

    @Override
    public void unlikeShot(TTEntity ttEntity) {
        mFabFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
    }

    @Override
    public void checklikeShot(boolean isLike) {
        if (isLike) {
            mFabFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
        } else {
            mFabFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
        }
    }

    @Override
    public void showError() {

    }
}
