package com.tyc.tdribbble.ui.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.entity.ShotsEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class UserInfoFragment extends Fragment {
    @BindView(R.id.tv_followers_count)
    TextView mTvFollowersCount;
    @BindView(R.id.tv_followings_count)
    TextView mTvFollowingsCount;
    @BindView(R.id.tv_favorite_count)
    TextView mTvFavoriteCount;
    @BindView(R.id.tv_buckets_count)
    TextView mTvBucketsCount;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tv_web)
    TextView mTvWeb;
    @BindView(R.id.tv_twitter)
    TextView mTvTwitter;

    Unbinder unbinder;
    @BindView(R.id.tv_projects_count)
    TextView mTvProjectsCount;
    @BindView(R.id.tv_shots_count)
    TextView mTvShotsCount;

    public static UserInfoFragment newInstance(ShotsEntity.UserBean user) {
        UserInfoFragment fragment = new UserInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_user_info, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ShotsEntity.UserBean user = (ShotsEntity.UserBean) getArguments().getSerializable("user");
        mTvFollowersCount.setText(user.getFollowers_count() + "粉丝");
        mTvFollowingsCount.setText(user.getFollowings_count() + "关注");
        // mTvFavoriteCount.setText(user.get+"点赞");
        mTvBucketsCount.setText(user.getBuckets_count() + "收藏");
        mTvShotsCount.setText(user.getShots_count() + "作品");
        mTvProjectsCount.setText(user.getProjects_count() + "项目");
        String location = user.getLocation();
        mTvLocation.setText(location);
        String web = user.getLinks().getWeb();
        if (TextUtils.isEmpty(web)) {
            mTvWeb.setVisibility(View.GONE);
        } else {
            mTvWeb.setText(web);
        }
        String twitter = user.getLinks().getTwitter();
        if (TextUtils.isEmpty(twitter)) {
            mTvTwitter.setVisibility(View.GONE);
        } else {
            mTvTwitter.setText(twitter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
