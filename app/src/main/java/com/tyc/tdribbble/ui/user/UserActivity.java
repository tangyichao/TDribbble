package com.tyc.tdribbble.ui.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.TFragmentPageAdapter;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.ui.user.followers.UserFollowersFragment;
import com.tyc.tdribbble.ui.user.fragment.UserInfoFragment;
import com.tyc.tdribbble.ui.user.fragment.UserWorksFragment;
import com.tyc.tdribbble.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class UserActivity extends BaseActivity {
    @BindView(R.id.iv_avatar_big)
    ImageView mIvAvatarBig;
    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_web)
    TextView mTvWeb;
    @BindView(R.id.tv_twitter)
    TextView mTvTwitter;
    @BindView(R.id.cl)
    CoordinatorLayout cl;
    @BindView(R.id.tl_user)
    TabLayout mTlUser;
    @BindView(R.id.vp_user)
    ViewPager mVpUser;

    private List<Fragment> list = new ArrayList<>();
    private String[] tabStrs = {"简介", "作品", "粉丝"};

    @Override
    protected int layoutResID() {
        return R.layout.activity_user;
    }

    @Override
    protected void initData() {
        int width = ScreenUtils.getScreenWidth(this);
        ViewGroup.LayoutParams params = mIvAvatarBig.getLayoutParams();
        params.height = width;
        params.width = width;
        mIvAvatarBig.setLayoutParams(params);
        ShotsEntity shots = (ShotsEntity) getIntent().getSerializableExtra("shots");
        String avatar = shots.getUser().getAvatar_url();
        Glide.with(this).load(avatar).into(mIvAvatar);
        Glide.with(this).load(avatar).bitmapTransform(new BlurTransformation(this, 18, 3)).override(width, width).into(mIvAvatarBig);
        String name = shots.getUser().getName();
        mTvName.setText(name);

        list.add(UserInfoFragment.newInstance(shots.getUser()));
        list.add(new UserWorksFragment());
        list.add(new UserFollowersFragment());
        mTlUser.setupWithViewPager(mVpUser);
        FragmentManager fm = getSupportFragmentManager();
        TFragmentPageAdapter adapter = new TFragmentPageAdapter(fm, tabStrs, list);
        mVpUser.setAdapter(adapter);

        String web = shots.getUser().getLinks().getWeb();
        if (TextUtils.isEmpty(web)) {
            mTvWeb.setVisibility(View.GONE);
        } else {
            mTvWeb.setText(web);
        }
        String twitter = shots.getUser().getLinks().getTwitter();
        if (TextUtils.isEmpty(twitter)) {
            mTvTwitter.setVisibility(View.GONE);
        } else {
            mTvTwitter.setText(twitter);
        }
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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
