package com.tyc.tdribbble.ui.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.TDribbbleApp;
import com.tyc.tdribbble.adapter.LinearShotsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.UserEntity;
import com.tyc.tdribbble.ui.about.AboutActivity;
import com.tyc.tdribbble.ui.login.LoginActivity;
import com.tyc.tdribbble.ui.myuser.shots.MyUserShotsActivity;
import com.tyc.tdribbble.ui.search.SearchActivity;
import com.tyc.tdribbble.ui.setting.SettingsActivity;
import com.tyc.tdribbble.ui.user.UserActivity;
import com.tyc.tdribbble.utils.DisplayUtils;
import com.tyc.tdribbble.utils.ScreenUtils;
import com.tyc.tdribbble.utils.StringOauth;
import com.tyc.tdribbble.utils.ThemeUtils;
import com.tyc.tdribbble.utils.TimeUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 作者：tangyc on 2017/6/20
 * 邮箱：874500641@qq.com
 */

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, IHomeView, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemSelectedListener {

    private static final int REQUEST_CODE = 1024;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    // @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    TextView mTvName;
    ImageView mIvBg;
    @BindView(R.id.sp_list)
    AppCompatSpinner mSpList;
    @BindView(R.id.sp_sort)
    AppCompatSpinner mSpSort;
    @BindView(R.id.sp_time)
    AppCompatSpinner mSpTime;
    @BindView(R.id.rv_shots)
    RecyclerView mRvShots;
    @BindView(R.id.srl_shots)
    SwipeRefreshLayout mSrlShots;
    @BindView(R.id.tv_empty_error)
    TextView mTvEmptyError;

    private LinearShotsAdapter adapter;
    private String token;
    private int pageCount = 1;
    private int perpage = 20;
    private HashMap<String, String> hashMap = new HashMap<>();
    private HomePresenter homePresenter;
    private boolean isFlag = false;
    private int type = 2; //默认是大的
    UserEntity userEntity;

    @Override
    protected int layoutResID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        token = TDribbbleApp.TOKEN;
        setSupportActionBar(mToolbar);
        View headerLayout = mNavView.getHeaderView(0);
        mIvAvatar = (CircleImageView) headerLayout.findViewById(R.id.iv_avatar);
        mIvAvatar.setOnClickListener(this);
        mTvName = (TextView) headerLayout.findViewById(R.id.tv_name);
        mTvName.setOnClickListener(this);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, getResources().getColor(R.color.colorPrimaryDark), StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        } else {
            StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, getResources().getColor(R.color.colorPrimaryDark), 255);
        }
        mIvBg = (ImageView) headerLayout.findViewById(R.id.iv_bg);
        MenuItem menuItem = mNavView.getMenu().getItem(0);
        menuItem.setChecked(true);//设置为选中状态
        mNavView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.selector_menu));
        mNavView.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.selector_menu));
        mSrlShots.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.i("debug", "onDrawerClosed");
                // StatusBarUtil.setColorForDrawerLayout(HomeActivity.this, mDrawerLayout, getResources().getColor(R.color.colorPrimaryDark), 255);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float f = 1 - slideOffset;

                StatusBarUtil.setColorForDrawerLayout(HomeActivity.this, mDrawerLayout, getResources().getColor(R.color.colorPrimaryDark), StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA + (int) (f * 143));
                Log.i("debug", StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA + (int) (f * 143) + "");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.i("debug", "onDrawerOpen");
                //StatusBarUtil.setColorForDrawerLayout(HomeActivity.this, mDrawerLayout, getResources().getColor(R.color.colorPrimaryDark), StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
            }
        };
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        int width = ScreenUtils.getScreenWidth(this);
        int height = DisplayUtils.dip2px(this, 56);
        mSpTime.setDropDownWidth(width / 3);//设置下拉菜单的宽度
        mSpTime.setDropDownHorizontalOffset(height);////设置选择微调的弹出窗口中像素的水平偏移。在mode_dropdown唯一有效的；
        mSpTime.setDropDownVerticalOffset(height);//设置选择微调的弹出窗口中像素的垂直偏移。在mode_dropdown唯一有效的；


        mSpList.setDropDownWidth(width / 3);//设置下拉菜单的宽度
        mSpList.setDropDownHorizontalOffset(height);////设置选择微调的弹出窗口中像素的水平偏移。在mode_dropdown唯一有效的；
        mSpList.setDropDownVerticalOffset(height);//设置选择微调的弹出窗口中像素的垂直偏移。在mode_dropdown唯一有效的；


        mSpSort.setDropDownWidth(width / 3);//设置下拉菜单的宽度
        mSpSort.setDropDownHorizontalOffset(height);////设置选择微调的弹出窗口中像素的水平偏移。在mode_dropdown唯一有效的；
        mSpSort.setDropDownVerticalOffset(height);//设置选择微调的弹出窗口中像素的垂直偏移。在mode_dropdown唯一有效的；


        homePresenter = new HomePresenter(this);
        mSpList.setOnItemSelectedListener(this);
        mSpSort.setOnItemSelectedListener(this);
        mSpTime.setOnItemSelectedListener(this);

        hashMap.put(ApiConstants.DATE, TimeUtils.dateToStr(TimeUtils.FORMAT_DATE, null));

        hashMap.put(ApiConstants.PERPAGE, String.valueOf(perpage));
        hashMap.put(ApiConstants.PAGE, String.valueOf(pageCount));
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        mRvShots.setLayoutManager(linearLayoutManager);

        mSrlShots.setOnRefreshListener(this);
        mRvShots.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                int count = recyclerView.getAdapter().getItemCount();
                if ((lastItemPosition == count - 2 || count < 2) && !isFlag) {
                    isFlag = true;
                    hashMap.remove(ApiConstants.PAGE);
                    pageCount++;
                    hashMap.put(ApiConstants.PAGE, String.valueOf(pageCount));
                    homePresenter.loadShots(HomeActivity.this, hashMap, 1);
                }
            }
        });
        if (!TextUtils.isEmpty(token)) {
            homePresenter.loadUser(this);
        }
    }

    private long time = 0;

    @Override
    public void onBackPressed() {
        //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            time = System.currentTimeMillis() - time;
            if (time > 1000) {
                Snackbar.make(mDrawerLayout, R.string.str_finish_app, Snackbar.LENGTH_LONG).show();
            } else {
                super.onBackPressed();
            }
            time = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_large_image) {
            type = 0;
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setAutoMeasureEnabled(true);
            mRvShots.setLayoutManager(linearLayoutManager);
            if (adapter != null) {
                adapter.chageType(type);
            }
            return true;
        }
        if (id == R.id.action_small_image) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRvShots.setLayoutManager(gridLayoutManager);
            type = 1;
            if (adapter != null) {
                adapter.chageType(type);
            }
            return true;
        }
        if (id == R.id.action_large_info) {
            type = 2;
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setAutoMeasureEnabled(true);
            mRvShots.setLayoutManager(linearLayoutManager);
            if (adapter != null) {
                adapter.chageType(type);
            }
            return true;
        }
        if (id == R.id.action_search) {
            Intent intent = new Intent();
            intent.setClass(this, SearchActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_shots) {
            // Handle the camera action
            if (TextUtils.isEmpty(token)) {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, LoginActivity.class);
                intent.putExtra("url", StringOauth.getOauthSting());
                startActivityForResult(intent, REQUEST_CODE, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } else {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, MyUserShotsActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra(ApiConstants.USERID, String.valueOf(userEntity.getId()));
                startActivity(intent);
            }

        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_favorite) {
            if (TextUtils.isEmpty(token)) {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, LoginActivity.class);
                intent.putExtra("url", StringOauth.getOauthSting());
                startActivityForResult(intent, REQUEST_CODE, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } else {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, MyUserShotsActivity.class);
                intent.putExtra("type", 2);
                intent.putExtra(ApiConstants.USERID, String.valueOf(userEntity.getId()));
                startActivity(intent);
            }
        } else if (id == R.id.nav_follower) {
            if (TextUtils.isEmpty(token)) {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, LoginActivity.class);
                intent.putExtra("url", StringOauth.getOauthSting());
                startActivityForResult(intent, REQUEST_CODE, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } else {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, MyUserShotsActivity.class);
                intent.putExtra("type", 3);
                intent.putExtra(ApiConstants.USERID, String.valueOf(userEntity.getId()));
                startActivity(intent);
            }
        } else if (id == R.id.nav_about) {
//           SpUtils.getSpUtils(this).putBoolean("dark_theme",!SpUtils.getSpUtils(this).getBoolean("dark_theme"));
//           ThemeUtils.notifyThemeApply(this);
            Intent intent = new Intent();
            intent.setClass(this, AboutActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent();
            intent.setClass(this, SettingsActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                if (TextUtils.isEmpty(token) || userEntity == null) {
                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this, LoginActivity.class);
                    intent.putExtra("url", StringOauth.getOauthSting());
                    startActivityForResult(intent, REQUEST_CODE, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                } else {
                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this, UserActivity.class);
                    intent.putExtra(ApiConstants.USER, userEntity);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
                            Pair.create((View) mIvAvatar, getResources().getString(R.string.str_avatar_tran)),
                            Pair.create((View) mTvName, getResources().getString(R.string.str_name_tran))).toBundle());
                }
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(UserEntity userEntity) {
        token = TDribbbleApp.TOKEN;
        this.userEntity = userEntity;
        Glide.with(this).load(userEntity.getAvatarUrl()).error(R.mipmap.ic_default_avatar).into(mIvAvatar);
        Glide.with(this).load(userEntity.getAvatarUrl()).bitmapTransform(new BlurTransformation(this, 18, 3)).into(mIvBg);
        mTvName.setText(userEntity.getName());
        mIvAvatar.setEnabled(true);
        TDribbbleApp.avatar = userEntity.getAvatarUrl();
    }

    @Override
    public void showShots(List<ShotsEntity> shotsEntities) {
        mRvShots.setVisibility(View.VISIBLE);
        mTvEmptyError.setVisibility(View.GONE);
        if (mRvShots.getAdapter() == null) {
            adapter = new LinearShotsAdapter(this, shotsEntities, type);
            mRvShots.setAdapter(adapter);
        } else {
            ((LinearShotsAdapter) mRvShots.getAdapter()).swipeData(shotsEntities);
        }
        mSrlShots.setRefreshing(false);
    }

    @Override
    public void loadMoreShots(List<ShotsEntity> shotsEntities) {
        if (mRvShots.getAdapter() == null) {
            adapter = new LinearShotsAdapter(this, shotsEntities, type);
            mRvShots.setAdapter(adapter);
        } else {
            ((LinearShotsAdapter) mRvShots.getAdapter()).addData(shotsEntities);
        }
        isFlag = false;

    }

    @Override
    public void showUser(UserEntity userEntity) {
        token = TDribbbleApp.TOKEN;
        this.userEntity = userEntity;
        Glide.with(this).load(userEntity.getAvatarUrl()).error(R.mipmap.ic_default_avatar).into(mIvAvatar);
        Glide.with(this).load(userEntity.getAvatarUrl()).bitmapTransform(new BlurTransformation(this, 18, 3)).into(mIvBg);
        mTvName.setText(userEntity.getName());
        mIvAvatar.setEnabled(true);
        TDribbbleApp.avatar = userEntity.getAvatarUrl();
    }

    @Override
    public void showError() {
        mSrlShots.setRefreshing(false);
        Snackbar.make(mRvShots, R.string.str_error, Snackbar.LENGTH_LONG).show();
        mRvShots.setVisibility(View.GONE);
        mTvEmptyError.setVisibility(View.VISIBLE);
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_error_result);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvEmptyError.setCompoundDrawables(null, drawable, null, null);
        mTvEmptyError.setText(R.string.str_error);
    }

    @Override
    public void showEmpty() {
        mSrlShots.setRefreshing(false);
        Snackbar.make(mRvShots, R.string.str_empty, Snackbar.LENGTH_LONG).show();
        mRvShots.setVisibility(View.GONE);
        mTvEmptyError.setVisibility(View.VISIBLE);
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_empty_result);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvEmptyError.setCompoundDrawables(null, drawable, null, null);
        mTvEmptyError.setText(R.string.str_empty);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.configThemeBeforeOnCreate(this, R.style.AppTheme_NoActionBar_Home, R.style.AppThemeLight_NoActionBar_Home);
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onRefresh() {
        homePresenter.loadShots(this, hashMap, 0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.sp_list:
                hashMap.put("list", ApiConstants.Shots.LIST_VALUES[i]);
                break;
            case R.id.sp_sort:
                hashMap.put("sort", ApiConstants.Shots.SORT_VALUES[i]);
                break;
            case R.id.sp_time:
                hashMap.put("timeframe", ApiConstants.Shots.TIME_VALUES[i]);
                break;
        }
//        TextView tv = (TextView)view;
//        tv.setGravity(Gravity.CENTER);
//
        homePresenter.loadShots(this, hashMap, 0);
        mSrlShots.setRefreshing(true);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void initToolbar() {
        super.initToolbar();

//                WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//                localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//                //将侧边栏顶部延伸至status bar
//                mDrawerLayout.setFitsSystemWindows(true);
//                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
//                mDrawerLayout.setClipToPadding(false);

    }
}
