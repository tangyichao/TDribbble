package com.tyc.tdribbble.ui.myuser.shots;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.ui.user.followers.UserFollowersFragment;
import com.tyc.tdribbble.ui.user.shots.UserShotsFragment;

/**
 * 作者：tangyc on 2017/7/10
 * 邮箱：874500641@qq.com
 */
public class MyUserShotsActivity extends BaseActivity {


    @Override
    protected int layoutResID() {
        return R.layout.activity_myuser_shots;
    }

    @Override
    protected void initData() {
        int type = getIntent().getIntExtra("type", 0);
        String userId = getIntent().getStringExtra(ApiConstants.USERID);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (type == 1) {

            UserShotsFragment userShotsFragment = UserShotsFragment.newInstance(userId);
            transaction.replace(R.id.frameLayout, userShotsFragment);
            transaction.commit();
        } else if (type == 2) {
            UserFollowersFragment userFollowersFragment = UserFollowersFragment.newInstance(userId);
            transaction.replace(R.id.frameLayout, userFollowersFragment);
            transaction.commit();
        }

    }
}
