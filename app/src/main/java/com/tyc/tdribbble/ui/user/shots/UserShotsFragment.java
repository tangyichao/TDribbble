package com.tyc.tdribbble.ui.user.shots;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.adapter.LinearShotsAdapter;
import com.tyc.tdribbble.entity.ShotsEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class UserShotsFragment extends Fragment implements IShotsView {
    @BindView(R.id.rv_followers)
    RecyclerView mRvFollowers;
    Unbinder unbinder;
    ShotsPresenter shotsPresenter;

    public static UserShotsFragment newInstance(String userId) {
        UserShotsFragment fragment = new UserShotsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("userId", userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_followers, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String userId = getArguments().getString("userId");
        shotsPresenter = new ShotsPresenter(this);
        shotsPresenter.loadShots(userId, TDribbbleApp.token);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRvFollowers.setLayoutManager(manager);
        new LinearSnapHelper().attachToRecyclerView(mRvFollowers);
    }

    @Override
    public void showShots(List<ShotsEntity> shotsEntities) {
        LinearShotsAdapter adapter = new LinearShotsAdapter(getActivity(), shotsEntities, 0);
        mRvFollowers.setAdapter(adapter);
    }

    @Override
    public void showError() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
