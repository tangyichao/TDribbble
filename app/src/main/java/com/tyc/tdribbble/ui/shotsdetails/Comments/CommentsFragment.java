package com.tyc.tdribbble.ui.shotsdetails.Comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.adapter.CommentsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseFragment;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.TTEntity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class CommentsFragment extends BaseFragment implements ICommentsView {
    @BindView(R.id.rv_comments)
    RecyclerView mRvComments;
    CommentsPresenter commentsPresenter;
    @BindView(R.id.iv_empty_error)
    ImageView mIvEmptyError;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    private boolean isFlag = false;
    private HashMap<String, String> hashMap = new HashMap<>();
    int pageNum = 1;
    private String shotId;

    public static CommentsFragment newInstance(String shotId) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ApiConstants.SHOTID, shotId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int layoutResID() {
        return R.layout.fragment_comments;
    }

    @Override
    public void initData() {
        shotId = getArguments().getString(ApiConstants.SHOTID);
        hashMap.put(ApiConstants.PAGE, String.valueOf(pageNum));
        commentsPresenter = new CommentsPresenter(this);
        commentsPresenter.loadComments(this,shotId, hashMap);
        mSrl.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSrl.setRefreshing(true);
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
                    commentsPresenter.loadComments(CommentsFragment.this,shotId, hashMap);
                    //mSrl.setRefreshing(true);
                }
            }
        });

    }

    @Override
    public void showComments(List<CommentsEntity> commentsEntities) {
        mRvComments.setVisibility(View.VISIBLE);
        if (mRvComments.getAdapter() == null) {
            CommentsAdapter adapter = new CommentsAdapter(getActivity(), commentsEntities);
            mRvComments.setAdapter(adapter);
            mRvComments.addItemDecoration(new DividerItemDecoration(
                    getActivity(), DividerItemDecoration.VERTICAL));
            mSrl.setRefreshing(false);
        } else {
            ((CommentsAdapter) mRvComments.getAdapter()).addData(commentsEntities);
        }
        ((CommentsAdapter) mRvComments.getAdapter()).setListener(new CommentsAdapter.OnClickLikeListener() {
            @Override
            public void onClick(Object object) {
                CommentsEntity entity = (CommentsEntity) object;
                commentsPresenter.likeComment(CommentsFragment.this,shotId, String.valueOf(entity.getId()));
            }
        });
        mIvEmptyError.setVisibility(View.GONE);
        isFlag = false;

    }

    @Override
    public void likeComment(TTEntity ttEntity) {
        ((CommentsAdapter) mRvComments.getAdapter()).swipeLike(ttEntity.getId());
    }

    @Override
    public void showEmpty() {
        mRvComments.setVisibility(View.GONE);
        mIvEmptyError.setVisibility(View.VISIBLE);
        mSrl.setRefreshing(false);
    }

    @Override
    public void showError() {
        mRvComments.setVisibility(View.GONE);
        mIvEmptyError.setVisibility(View.VISIBLE);
        mIvEmptyError.setImageResource(R.mipmap.ic_error_result);
        mSrl.setRefreshing(false);
    }

}
