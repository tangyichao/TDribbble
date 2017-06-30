package com.tyc.tdribbble.ui.shotsdetails.attachments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.adapter.AttachmentsAdapter;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.AttachmentsEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：tangyc on 2017/6/30
 * 邮箱：874500641@qq.com
 */
public class AttachmentsActivity extends BaseActivity implements IAttachmentsView {
    @BindView(R.id.rv_attachments)
    RecyclerView mRvAttachments;
    @BindView(R.id.sfl)
    SwipeRefreshLayout mSfl;

    @Override
    protected int layoutResID() {
        return R.layout.activity_attachments;
    }

    @Override
    protected void initData() {
        int shotId = getIntent().getIntExtra("shotId", 0);
        mSfl.setRefreshing(true);
        mSfl.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        AttachmentsPresenter presenter = new AttachmentsPresenter(this);
        presenter.loadAttachments(String.valueOf(shotId), TDribbbleApp.token);
        mRvAttachments.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showAttachments(List<AttachmentsEntity> attachmentsEntities) {
        mRvAttachments.setAdapter(new AttachmentsAdapter(this, attachmentsEntities));
        mRvAttachments.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
        mSfl.setRefreshing(false);
    }

    @Override
    public void showError() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
