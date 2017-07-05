package com.tyc.tdribbble.ui.user.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseFragment;
import com.tyc.tdribbble.entity.UserEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class UserInfoFragment extends BaseFragment {
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

    @BindView(R.id.tv_projects_count)
    TextView mTvProjectsCount;
    @BindView(R.id.tv_shots_count)
    TextView mTvShotsCount;
    @BindView(R.id.tv_comments_count)
    TextView mTvCommentsCount;
    @BindView(R.id.tv_teams_count)
    TextView mTvTeamsCount;

    private UserEntity user;
    public static UserInfoFragment newInstance(UserEntity user) {
        UserInfoFragment fragment = new UserInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ApiConstants.USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int layoutResID() {
        return R.layout.fragment_user_info;
    }

    @Override
    public void initData() {
        user = (UserEntity) getArguments().getSerializable(ApiConstants.USER);
        mTvFollowersCount.setText(user.getFollowersCount() + "粉丝");
        mTvFollowingsCount.setText(user.getFollowingsCount() + "关注");
        mTvFavoriteCount.setText(user.getLikesCount() + "点赞");
        mTvBucketsCount.setText(user.getBucketsCount() + "收藏");
        mTvShotsCount.setText(user.getShotsCount() + "作品");
        mTvProjectsCount.setText(user.getProjectsCount() + "项目");
        mTvTeamsCount.setText(user.getTeamsCount() + "团队");
        mTvCommentsCount.setText(user.getLikesReceivedCount() + "被喜欢");
        String location = user.getLocation();
        if (TextUtils.isEmpty(location)) {
            mTvLocation.setVisibility(View.GONE);
        } else {
            mTvLocation.setText(location);
        }
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

    @OnClick({R.id.tv_web, R.id.tv_twitter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_web: {
                openUri(user.getLinks().getWeb());
                break;
            }
            case R.id.tv_twitter:
                openUri(user.getLinks().getTwitter());
                break;
        }
    }
    private void openUri(String uriStr) {
        Uri uri = Uri.parse(uriStr);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }
}
