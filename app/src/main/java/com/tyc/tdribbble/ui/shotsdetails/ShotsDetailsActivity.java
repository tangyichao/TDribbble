package com.tyc.tdribbble.ui.shotsdetails;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.flexbox.FlexboxLayout;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.CommentsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.TTEntity;
import com.tyc.tdribbble.entity.UserEntity;
import com.tyc.tdribbble.ui.bigimage.BigImageActivity;
import com.tyc.tdribbble.ui.search.SearchActivity;
import com.tyc.tdribbble.ui.shotsdetails.attachments.AttachmentsActivity;
import com.tyc.tdribbble.ui.user.UserActivity;
import com.tyc.tdribbble.utils.DisplayUtils;
import com.tyc.tdribbble.utils.HtmlFormatUtils;
import com.tyc.tdribbble.utils.ScreenUtils;
import com.tyc.tdribbble.utils.TimeUtils;
import com.tyc.tdribbble.view.widget.BadgedFourThreeImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class ShotsDetailsActivity extends BaseActivity implements IShotsDetailsView {
    @BindView(R.id.iv_shots)
    BadgedFourThreeImageView mIvShots;

    @BindView(R.id.fab_favorite)
    FloatingActionButton mFabFavorite;
    @BindView(R.id.cl)
    CoordinatorLayout cl;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    ShotsEntity shots;
    @BindView(R.id.rv_comments)
    RecyclerView mRvComments;

    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.fl_tags)
    FlexboxLayout mFlTags;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.tv_attachments)
    TextView mTvAttachments;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar_author)
    CircleImageView mIvAvatarAuthor;
    @BindView(R.id.tv_favorite_count)
    TextView mTvFavoriteCount;
    @BindView(R.id.tv_views_count)
    TextView mTvViewsCount;
    @BindView(R.id.tv_buckets_count)
    TextView mTvBucketsCount;
    @BindView(R.id.tv_comments_count)
    TextView mTvCommentsCount;
    @BindView(R.id.nsv)
    NestedScrollView mNsv;
    @BindView(R.id.iv_create_comment)
    ImageView mIvCreateComment;
    @BindView(R.id.et_comment)
    AppCompatEditText mEtComment;
    @BindView(R.id.til_comment)
    TextInputLayout mTilComment;
    @BindView(R.id.iv_error_empty)
    ImageView mIvErrorEmpty;
    @BindView(R.id.ctl_shots)
    CollapsingToolbarLayout mCtlShots;

    private ShotsDetailsPresenter presenter;
    private HashMap<String, String> hashMap = new HashMap<>();
    int pageNum = 1;
    int perpage = 20;
    private boolean isFlag = false;

    @Override
    protected int layoutResID() {
        return R.layout.activity_shots_details;
    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mCtlShots.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
        final int width = ScreenUtils.getScreenWidth(this);
        ViewGroup.LayoutParams params = mIvShots.getLayoutParams();
        params.width = width;
        params.height = width * 3 / 4;
        mIvShots.setLayoutParams(params);
        shots = (ShotsEntity) getIntent().getSerializableExtra(ApiConstants.SHOTS);
        final boolean isAnimated = shots.isAnimated();
        if (isAnimated) {
            String hidpi = shots.getImages().getHidpi();
            Glide.with(ShotsDetailsActivity.this).load(hidpi).asGif().placeholder(R.drawable.bg_linear_shots).diskCacheStrategy(DiskCacheStrategy.SOURCE).dontTransform().fitCenter().into(mIvShots);
        } else {
            String normal = shots.getImages().getNormal();
            Glide.with(ShotsDetailsActivity.this).load(normal).placeholder(R.drawable.bg_linear_shots).override(width, width * 3 / 4).into(mIvShots);
        }
        String title = shots.getTitle();
        setTitle(title);
        presenter = new ShotsDetailsPresenter(this);
        hashMap.put(ApiConstants.PAGE, String.valueOf(pageNum));

        hashMap.put(ApiConstants.PERPAGE, String.valueOf(perpage));
        if (TextUtils.isEmpty(shots.getCreatedAt()) || shots.getUser() == null) {
            presenter.loadShotsIntroduction(this, String.valueOf(shots.getId()));
        } else {
            showShot(shots);
        }
        presenter.loadComments(this, String.valueOf(shots.getId()), hashMap);
        presenter.checklikeShot(this, String.valueOf(shots.getId()));

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mRvComments.setLayoutManager(linearLayoutManager);
        mRvComments.setHasFixedSize(true);
        mRvComments.setNestedScrollingEnabled(false);

        mNsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) && !isFlag) {
                    // 底部
                    isFlag = true;
                    hashMap.remove(ApiConstants.PAGE);
                    pageNum++;
                    hashMap.put(ApiConstants.PAGE, String.valueOf(pageNum));
                    presenter.loadComments(ShotsDetailsActivity.this, String.valueOf(shots.getId()), hashMap);
                }
            }
        });
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

    @Override
    protected void initToolbar() {
        super.initToolbar();
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_shots, R.id.fab_favorite, R.id.iv_avatar, R.id.tv_attachments, R.id.iv_create_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_shots: {
                Intent intent = new Intent();
                intent.setClass(this, BigImageActivity.class);
                intent.putExtra(ApiConstants.SHOTS, shots);
                if (shots.isAnimated()) {
                    startActivity(intent);
                } else {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, mIvShots, getResources().getString(R.string.str_shots_tran)).toBundle());
                }
                break;
            }
            case R.id.fab_favorite: {
                presenter.likeShot(this, String.valueOf(shots.getId()));
                break;
            }
            case R.id.iv_avatar: {
                Intent intent = new Intent();
                intent.setClass(this, UserActivity.class);
                intent.putExtra(ApiConstants.USER, shots.getUser());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
                        Pair.create((View) mIvAvatar, getResources().getString(R.string.str_avatar_tran)),
                        Pair.create((View) mTvName, getResources().getString(R.string.str_name_tran))).toBundle());
                break;
            }
            case R.id.tv_attachments: {
                Intent intent = new Intent();
                intent.setClass(this, AttachmentsActivity.class);
                intent.putExtra(ApiConstants.SHOTID, shots.getId());
                startActivity(intent);
                break;
            }
            case R.id.iv_create_comment: {
                String comment = mEtComment.getText().toString().trim();
                if (TextUtils.isEmpty(comment)) {
                    mTilComment.setError(getString(R.string.str_empty));
                } else {
                    presenter.createComment(this, String.valueOf(shots.getId()), comment);
                }
                break;
            }
        }
    }


    @Override
    public void likeShot(TTEntity ttEntity) {
        mFabFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
        Integer count = Integer.valueOf(mTvFavoriteCount.getText().toString().trim()) + 1;
        mTvFavoriteCount.setText(String.valueOf(count));
        Drawable drawable = getResources().getDrawable(R.drawable.ic_favorite_red_24dp);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvFavoriteCount.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    public void unlikeShot(TTEntity ttEntity) {
        mFabFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
    }

    @Override
    public void checklikeShot(boolean isLike) {
        if (isLike) {
            mFabFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
            Drawable drawable = getResources().getDrawable(R.drawable.ic_favorite_red_24dp);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTvFavoriteCount.setCompoundDrawables(drawable, null, null, null);
        } else {
            mFabFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showComments(List<CommentsEntity> commentsEntities) {
        if (commentsEntities.size() == perpage) {
            isFlag = false; //滑动的话可以继续加载
        }
        mRvComments.setVisibility(View.VISIBLE);
        mIvErrorEmpty.setVisibility(View.GONE);
        if (mRvComments.getAdapter() == null) {
            CommentsAdapter adapter = new CommentsAdapter(this, commentsEntities);
            mRvComments.setAdapter(adapter);
            mRvComments.addItemDecoration(new DividerItemDecoration(
                    this, DividerItemDecoration.VERTICAL));
        } else {
            ((CommentsAdapter) mRvComments.getAdapter()).addData(commentsEntities);
        }
        ((CommentsAdapter) mRvComments.getAdapter()).setListener(new CommentsAdapter.OnClickLikeListener() {
            @Override
            public void onClick(Object object) {
                CommentsEntity entity = (CommentsEntity) object;
                presenter.likeComment(ShotsDetailsActivity.this, String.valueOf(shots.getId()), String.valueOf(entity.getId()));
            }
        });

    }

    @Override
    public void likeComment(TTEntity ttEntity) {

    }

    @Override
    public void showEmpty() {

    }


    @Override
    public void showShotsIntroduction(ShotsEntity shotsEntity) {
        showShot(shotsEntity);
    }

    @Override
    public void createComment(CommentsEntity commentsEntity) {
        List<CommentsEntity> commentsEntities = new ArrayList<>();
        commentsEntities.add(commentsEntity);
        mRvComments.setVisibility(View.VISIBLE);
        if (mRvComments.getAdapter() == null) {
            CommentsAdapter adapter = new CommentsAdapter(this, commentsEntities);
            mRvComments.setAdapter(adapter);
            mRvComments.addItemDecoration(new DividerItemDecoration(
                    this, DividerItemDecoration.VERTICAL));
        } else {
            ((CommentsAdapter) mRvComments.getAdapter()).addData(commentsEntities);
        }
    }

    @Override
    public void showCommentsEmpty() {
        mRvComments.setVisibility(View.GONE);
        mIvErrorEmpty.setVisibility(View.VISIBLE);
        mIvErrorEmpty.setImageResource(R.mipmap.ic_empty_result);
    }

    @Override
    public void showCommentsError() {
        mRvComments.setVisibility(View.GONE);
        mIvErrorEmpty.setVisibility(View.VISIBLE);
        mIvErrorEmpty.setImageResource(R.mipmap.ic_error_result);
    }

    private void showShot(ShotsEntity shots) {

        UserEntity userEntity = shots.getUser();
        if (userEntity != null) {
            String avatar = userEntity.getAvatarUrl();
            Glide.with(this).load(avatar).into(mIvAvatar);
            String name = userEntity.getName();
            mTvName.setText(name);
            String location = userEntity.getLocation();
            if (TextUtils.isEmpty(location)) {
                mTvLocation.setVisibility(View.GONE);
            } else {
                mTvLocation.setVisibility(View.VISIBLE);
                mTvLocation.setText(location);
            }
        }
        mTvCommentsCount.setText(String.valueOf(shots.getCommentsCount()));
        mTvViewsCount.setText(String.valueOf(shots.getViewsCount()));
        mTvBucketsCount.setText(String.valueOf(shots.getBucketsCount()));
        mTvFavoriteCount.setText(String.valueOf(shots.getLikesCount()));
        String time = shots.getUpdatedAt();
        mTvTime.setText(TimeUtils.getTimeFromISO8601(time) + "创建");
        if (shots.getTags() != null) {
            for (int i = 0; i < shots.getTags().size(); i++) {
                String tag = shots.getTags().get(i);
                mFlTags.addView(createNewFlexItemTextView(tag));
            }
        }
        String desc = shots.getDescription();
        if (!TextUtils.isEmpty(desc))
            HtmlFormatUtils.Html2StringNoP(mTvDesc, desc);
        int attachmentsCount = shots.getAttachmentsCount();
        if (attachmentsCount > 0) {
            mTvAttachments.setVisibility(View.VISIBLE);
            mTvAttachments.setText(attachmentsCount + "个插件");
        } else {
            mTvAttachments.setVisibility(View.GONE);
        }
    }

    private TextView createNewFlexItemTextView(final String tag) {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(tag);
        textView.setTextSize(14);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        textView.setBackgroundResource(R.drawable.bg_linear_tag);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ShotsDetailsActivity.this, SearchActivity.class);
                intent.putExtra("search", tag);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ShotsDetailsActivity.this).toBundle());
            }
        });
        int padding = DisplayUtils.dip2px(this, 6);
        int paddingLeftAndRight = DisplayUtils.dip2px(this, 8);
        ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = DisplayUtils.dip2px(this, 6);
        int marginTop = DisplayUtils.dip2px(this, 16);
        layoutParams.setMargins(margin, marginTop, margin, 0);
        textView.setLayoutParams(layoutParams);
        return textView;
    }


}

