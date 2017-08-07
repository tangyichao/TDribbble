package com.tyc.tdribbble.ui.user.shots;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.adapter.LinearShotsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiService;
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
public class UserShotsFragment extends BaseFragment implements IShotsView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_shots)
    RecyclerView mRvShots;
    ShotsPresenter shotsPresenter;
    @BindView(R.id.tv_empty_error)
    TextView mTvEmptyError;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    private String[] user;
    public static UserShotsFragment newInstance(String userId, int shotsType) {
        UserShotsFragment fragment = new UserShotsFragment();
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
        user = bundle.getStringArray(ApiConstants.USERID);
        shotsPresenter = new ShotsPresenter(this);
        shotsPresenter.loadShots(this, user[0], Integer.valueOf(user[1]));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRvShots.setLayoutManager(manager);
        //  new LinearSnapHelper().attachToRecyclerView(mRvShots);
        mSrl.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSrl.setRefreshing(true);
        mSrl.setOnRefreshListener(this);
    }

    @Override
    public void showShots(List<ShotsEntity> shotsEntities) {

        if (shotsEntities.size() > 0) {
            mRvShots.setVisibility(View.VISIBLE);
            LinearShotsAdapter adapter = new LinearShotsAdapter(getActivity(), shotsEntities, 0);
            mRvShots.setAdapter(adapter);
            mTvEmptyError.setVisibility(View.GONE);
        } else {
            mRvShots.setVisibility(View.GONE);
            mTvEmptyError.setVisibility(View.VISIBLE);
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_empty_result);
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
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_error_result);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvEmptyError.setCompoundDrawables(null, drawable, null, null);
        mTvEmptyError.setText(R.string.str_error);
        mSrl.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        shotsPresenter.loadShots(this, user[0], Integer.valueOf(user[1]));
    }
}
