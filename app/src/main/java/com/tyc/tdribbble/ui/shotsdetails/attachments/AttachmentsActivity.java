package com.tyc.tdribbble.ui.shotsdetails.attachments;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.AttachmentsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
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
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int layoutResID() {
        return R.layout.activity_attachments;
    }

    @Override
    protected void initData() {
        int shotId = getIntent().getIntExtra(ApiConstants.SHOTID, 0);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mSfl.setRefreshing(true);
        mSfl.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        AttachmentsPresenter presenter = new AttachmentsPresenter(this);
        presenter.loadAttachments(this, String.valueOf(shotId));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
