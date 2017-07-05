package com.tyc.tdribbble.ui.user.shots;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.adapter.LinearShotsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseFragment;
import com.tyc.tdribbble.entity.ShotsEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class UserShotsFragment extends BaseFragment implements IShotsView {
    @BindView(R.id.rv_followers)
    RecyclerView mRvFollowers;
    ShotsPresenter shotsPresenter;
    @BindView(R.id.iv_empty_error)
    ImageView mIvEmptyError;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;

    public static UserShotsFragment newInstance(String userId) {
        UserShotsFragment fragment = new UserShotsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ApiConstants.USERID, userId);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int layoutResID() {
        return R.layout.fragment_followers;
    }

    @Override
    public void initData() {
        String userId = getArguments().getString(ApiConstants.USERID);
        shotsPresenter = new ShotsPresenter(this);
        shotsPresenter.loadShots(userId);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRvFollowers.setLayoutManager(manager);
        new LinearSnapHelper().attachToRecyclerView(mRvFollowers);
    }

    @Override
    public void showShots(List<ShotsEntity> shotsEntities) {

        if (shotsEntities.size() > 0) {
            mRvFollowers.setVisibility(View.VISIBLE);
            LinearShotsAdapter adapter = new LinearShotsAdapter(getActivity(), shotsEntities, 0);
            mRvFollowers.setAdapter(adapter);
            mIvEmptyError.setVisibility(View.GONE);
        } else {
            mRvFollowers.setVisibility(View.GONE);
            mIvEmptyError.setVisibility(View.VISIBLE);
        }
        mSrl.setRefreshing(false);
    }

    @Override
    public void showError() {
        mRvFollowers.setVisibility(View.GONE);
        mIvEmptyError.setVisibility(View.VISIBLE);
        mIvEmptyError.setImageResource(R.mipmap.ic_error_result);
        mSrl.setRefreshing(false);
    }

}
