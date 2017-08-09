package com.tyc.tdribbble.ui.user.followers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.FollowersAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseFragment;
import com.tyc.tdribbble.entity.FollowersEntity;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class UserFollowersFragment extends BaseFragment implements IFollowersView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_followers)
    RecyclerView mRvFollowers;
    FollowersPresenter followersPresenter;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.tv_empty_error)
    TextView mTvEmptyError;
    private String userId;
    public static UserFollowersFragment newInstance(String userId) {
        UserFollowersFragment fragment = new UserFollowersFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ApiConstants.USERID, userId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int layoutResID() {
        return R.layout.fragment_followers;
    }

    @Override
    public void initData() {
        userId = getArguments().getString(ApiConstants.USERID);
        followersPresenter = new FollowersPresenter(this);
        followersPresenter.loadFollowers(this,userId);
        mRvFollowers.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSrl.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mSrl.setRefreshing(true);
        mSrl.setOnRefreshListener(this);
    }

    @Override
    public void showFollowers(List<FollowersEntity> followersEntities) {
        if (followersEntities.size() > 0) {
            mRvFollowers.setVisibility(View.VISIBLE);
            mRvFollowers.setAdapter(new FollowersAdapter(getActivity(), followersEntities));
            mRvFollowers.addItemDecoration(new DividerItemDecoration(
                    getActivity(), DividerItemDecoration.VERTICAL));
            mTvEmptyError.setVisibility(View.GONE);
        } else {
            mRvFollowers.setVisibility(View.GONE);
            mTvEmptyError.setVisibility(View.VISIBLE);
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_empty_result);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTvEmptyError.setCompoundDrawables(null, drawable, null, null);
            mTvEmptyError.setText(R.string.str_empty);
        }
        mSrl.setRefreshing(false);
    }

    @Override
    public void showError() {
        mRvFollowers.setVisibility(View.GONE);
        mTvEmptyError.setVisibility(View.VISIBLE);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_error_result);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvEmptyError.setCompoundDrawables(null, drawable, null, null);
        mTvEmptyError.setText(R.string.str_error);
        mSrl.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        followersPresenter.loadFollowers(this, userId);
    }
}
