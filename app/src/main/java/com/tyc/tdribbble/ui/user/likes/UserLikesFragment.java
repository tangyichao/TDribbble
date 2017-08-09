package com.tyc.tdribbble.ui.user.likes;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.LinearLikesAdapter;
import com.tyc.tdribbble.adapter.LinearShotsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseFragment;
import com.tyc.tdribbble.entity.LikesEntity;
import com.tyc.tdribbble.entity.ShotsEntity;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class UserLikesFragment extends BaseFragment implements ILikesView {
    @BindView(R.id.rv_shots)
    RecyclerView mRvShots;
    LikesPresenter shotsPresenter;
    @BindView(R.id.tv_empty_error)
    TextView mTvEmptyError;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;

    public static UserLikesFragment newInstance(String userId, int shotsType) {
        UserLikesFragment fragment = new UserLikesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ApiConstants.USERID, userId);
        //  bundle.putInt(ApiConstants.LIKES,shotsType);
        bundle.putStringArray(ApiConstants.USERID, new String[]{userId, String.valueOf(shotsType)});
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int layoutResID() {
        return R.layout.fragment_shots;
    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        String[] user = bundle.getStringArray(ApiConstants.USERID);

        shotsPresenter = new LikesPresenter(this);
        shotsPresenter.loadShots(this, user[0], Integer.valueOf(user[1]));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRvShots.setLayoutManager(manager);
        //  new LinearSnapHelper().attachToRecyclerView(mRvShots);
    }

    @Override
    public void showLikes(List<LikesEntity> likesEntities) {

        if (likesEntities.size() > 0) {
            mRvShots.setVisibility(View.VISIBLE);
            LinearLikesAdapter adapter = new LinearLikesAdapter(getActivity(), likesEntities, 0);
            mRvShots.setAdapter(adapter);
            mTvEmptyError.setVisibility(View.GONE);
        } else {
            mRvShots.setVisibility(View.GONE);
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
        mRvShots.setVisibility(View.GONE);
        mTvEmptyError.setVisibility(View.VISIBLE);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_error_result);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvEmptyError.setCompoundDrawables(null, drawable, null, null);
        mTvEmptyError.setText(R.string.str_error);
        mSrl.setRefreshing(false);
    }

}
