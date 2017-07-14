package com.tyc.tdribbble.ui.user.followers;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

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
public class UserFollowersFragment extends BaseFragment implements IFollowersView {
    @BindView(R.id.rv_followers)
    RecyclerView mRvFollowers;
    FollowersPresenter followersPresenter;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.iv_empty_error)
    ImageView mIvEmptyError;

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
        String userId = getArguments().getString(ApiConstants.USERID);
        followersPresenter = new FollowersPresenter(this);
        followersPresenter.loadFollowers(this,userId);
        mRvFollowers.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSrl.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSrl.setRefreshing(true);
    }

    @Override
    public void showFollowers(List<FollowersEntity> followersEntities) {
        if (followersEntities.size() > 0) {
            mRvFollowers.setVisibility(View.VISIBLE);
            mRvFollowers.setAdapter(new FollowersAdapter(getActivity(), followersEntities));
            mRvFollowers.addItemDecoration(new DividerItemDecoration(
                    getActivity(), DividerItemDecoration.VERTICAL));
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
