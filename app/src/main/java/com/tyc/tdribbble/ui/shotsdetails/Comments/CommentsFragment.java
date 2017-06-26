package com.tyc.tdribbble.ui.shotsdetails.Comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.adapter.CommentsAdapter;
import com.tyc.tdribbble.adapter.FollowersAdapter;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.FollowersEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class CommentsFragment extends Fragment implements ICommentsView {
    @BindView(R.id.rv_comments)
    RecyclerView mRvComments;
    Unbinder unbinder;
    CommentsPresenter commentsPresenter;

    public static CommentsFragment newInstance(String shotId) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("shotId", shotId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_comments, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String shotId = getArguments().getString("shotId");
        Log.i("debug", shotId);
        commentsPresenter = new CommentsPresenter(this);
        commentsPresenter.loadComments(shotId, TDribbbleApp.token);
        mRvComments.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void showComments(List<CommentsEntity> commentsEntities) {
        mRvComments.setAdapter(new CommentsAdapter(getActivity(), commentsEntities));
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
