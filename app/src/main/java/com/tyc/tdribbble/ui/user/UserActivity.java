package com.tyc.tdribbble.ui.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.TFragmentPageAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.UserEntity;
import com.tyc.tdribbble.ui.user.followers.UserFollowersFragment;
import com.tyc.tdribbble.ui.user.fragment.UserInfoFragment;
import com.tyc.tdribbble.ui.user.shots.UserShotsFragment;
import com.tyc.tdribbble.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.cl)
    CoordinatorLayout cl;
    @BindView(R.id.tl_user)
    TabLayout mTlUser;
    @BindView(R.id.vp_user)
    ViewPager mVpUser;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;


    private List<Fragment> list = new ArrayList<>();
    private String[] tabStrs = {"简介", "作品", "粉丝"};

    @Override
    protected int layoutResID() {
        return R.layout.activity_user;
    }

    @Override
    protected void initData() {
//        setSupportActionBar(mToolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
        int width = ScreenUtils.getScreenWidth(this);
        ViewGroup.LayoutParams params = mIvAvatarBig.getLayoutParams();
        params.height = width;
        params.width = width;
        mIvAvatarBig.setLayoutParams(params);
        UserEntity user = (UserEntity) getIntent().getSerializableExtra(ApiConstants.USER);
        String avatar = user.getAvatarUrl();
        Glide.with(this).load(avatar).into(mIvAvatar);
        Glide.with(this).load(avatar).bitmapTransform(new BlurTransformation(this, 20)).override(width, width).into(mIvAvatarBig);


        String name = user.getName();
        mTvName.setText(name);
        // setTitle(name);
        list.add(UserInfoFragment.newInstance(user));
        list.add(UserShotsFragment.newInstance(String.valueOf(user.getId())));
        list.add(UserFollowersFragment.newInstance(String.valueOf(user.getId())));
        mTlUser.setupWithViewPager(mVpUser);
        FragmentManager fm = getSupportFragmentManager();
        TFragmentPageAdapter adapter = new TFragmentPageAdapter(fm, tabStrs, list);
        mVpUser.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
//        int centerX = (mIvAvatarBig.getLeft() + mIvAvatarBig.getRight()) / 2;
//        int centerY = (mIvAvatarBig.getTop() + mIvAvatarBig.getBottom()) / 2;
//        // Math.hypot(x,y):  返回sqrt(x2 +y2)
//        // 获取扩散的半径
//        float finalRadius = (float) Math.hypot((double) centerX, (double) centerY);
//        // 定义揭露动画
//        Animator mCircularReveal = ViewAnimationUtils.createCircularReveal(
//                mIvAvatarBig, centerX, centerY, 0, finalRadius);
//        // 设置动画持续时间，并开始动画
//        mCircularReveal.setDuration(230).start();
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


    @OnClick({R.id.iv_avatar_big, R.id.iv_avatar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar_big:
                break;
            case R.id.iv_avatar:
                break;
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
