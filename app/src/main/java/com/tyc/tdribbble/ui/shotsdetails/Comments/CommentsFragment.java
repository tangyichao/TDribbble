package com.tyc.tdribbble.ui.shotsdetails.Comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.adapter.CommentsAdapter;
import com.tyc.tdribbble.adapter.LinearShotsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.entity.CommentsEntity;

import java.util.HashMap;
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
    @BindView(R.id.pb_loading_more)
    ProgressBar mPbLoadingMore;
    Unbinder unbinder;
    CommentsPresenter commentsPresenter;
    private boolean isFlag = false;
    private HashMap<String, String> hashMap = new HashMap<>();
    int pageNum = 1;
    private String shotId;
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
        shotId = getArguments().getString("shotId");
        hashMap.put(ApiConstants.PAGE, String.valueOf(pageNum));
        commentsPresenter = new CommentsPresenter(this);
        commentsPresenter.loadComments(shotId, hashMap, TDribbbleApp.token);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setAutoMeasureEnabled(true);
        mRvComments.setLayoutManager(linearLayoutManager);
        mRvComments.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                int itemCount = recyclerView.getAdapter().getItemCount();
                int childCount = recyclerView.getChildCount();
                if ((itemCount - childCount) <= lastItemPosition && !isFlag) {
                    isFlag = true;
                    pageNum++;
                    hashMap.put(ApiConstants.PAGE, String.valueOf(pageNum));
                    commentsPresenter.loadComments(shotId, hashMap, TDribbbleApp.token);
                }
            }
        });

    }

    @Override
    public void showComments(List<CommentsEntity> commentsEntities) {
        if (mRvComments.getAdapter() == null) {
            CommentsAdapter adapter = new CommentsAdapter(getActivity(), commentsEntities);
            mRvComments.setAdapter(adapter);
            mRvComments.addItemDecoration(new DividerItemDecoration(
                    getActivity(), DividerItemDecoration.VERTICAL));
        } else {
            ((CommentsAdapter) mRvComments.getAdapter()).addData(commentsEntities);
        }
        isFlag = false;
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
