package com.tyc.tdribbble.ui.myuser.shots;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.ui.user.followers.UserFollowersFragment;
import com.tyc.tdribbble.ui.user.likes.UserLikesFragment;
import com.tyc.tdribbble.ui.user.shots.UserShotsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：tangyc on 2017/7/10
 * 邮箱：874500641@qq.com
 */
public class MyUserShotsActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int layoutResID() {
        return R.layout.activity_myuser_shots;
    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        int type = getIntent().getIntExtra("type", 0);
        String userId = getIntent().getStringExtra(ApiConstants.USERID);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (type == 1) {
            setTitle(R.string.str_shots);
            UserShotsFragment userShotsFragment = UserShotsFragment.newInstance(userId, 1);
            transaction.replace(R.id.frameLayout, userShotsFragment);
            transaction.commit();
        } else if (type == 2) {
            setTitle(R.string.str_favorite);
//            UserShotsFragment userShotsFragment = UserShotsFragment.newInstance(userId, 2);
//            transaction.replace(R.id.frameLayout, userShotsFragment);
//            transaction.commit();
            UserLikesFragment userLikesFragment = UserLikesFragment.newInstance(userId, 2);
            transaction.replace(R.id.frameLayout, userLikesFragment);
            transaction.commit();
        } else if (type == 3) {
            setTitle(R.string.str_follower);
            UserFollowersFragment userFollowersFragment = UserFollowersFragment.newInstance(userId);
            transaction.replace(R.id.frameLayout, userFollowersFragment);
            transaction.commit();
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
