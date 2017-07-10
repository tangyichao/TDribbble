package com.tyc.tdribbble.ui.myuser.shots;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.LinearShotsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.ui.user.shots.IShotsView;
import com.tyc.tdribbble.ui.user.shots.ShotsPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：tangyc on 2017/7/10
 * 邮箱：874500641@qq.com
 */
public class MyUserShotsActivity extends BaseActivity implements IShotsView {
    @BindView(R.id.rv_shots)
    RecyclerView mRvShots;
    @BindView(R.id.iv_empty_error)
    ImageView mIvEmptyError;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    ShotsPresenter shotsPresenter;
    @Override
    protected int layoutResID() {
        return R.layout.activity_myuser_shots;
    }

    @Override
    protected void initData() {
        String userId = getIntent().getStringExtra(ApiConstants.USERID);
        Log.i("debug",userId+"========");
        shotsPresenter = new ShotsPresenter(this);
      //  shotsPresenter.loadShots(this,userId);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvShots.setLayoutManager(manager);
        new LinearSnapHelper().attachToRecyclerView(mRvShots);
    }

    @Override
    public void showShots(List<ShotsEntity> shotsEntities) {
        if (shotsEntities.size() > 0) {
            mRvShots.setVisibility(View.VISIBLE);
            LinearShotsAdapter adapter = new LinearShotsAdapter(this, shotsEntities, 0);
            mRvShots.setAdapter(adapter);
            mIvEmptyError.setVisibility(View.GONE);
        } else {
            mRvShots.setVisibility(View.GONE);
            mIvEmptyError.setVisibility(View.VISIBLE);
        }
        mSrl.setRefreshing(false);
    }

    @Override
    public void showError() {
        mRvShots.setVisibility(View.GONE);
        mIvEmptyError.setVisibility(View.VISIBLE);
        mIvEmptyError.setImageResource(R.mipmap.ic_error_result);
        mSrl.setRefreshing(false);
    }

}
