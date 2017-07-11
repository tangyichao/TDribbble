package com.tyc.tdribbble.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
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
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.tyc.tdribbble.ui.user.UserActivity;
import com.tyc.tdribbble.utils.DisplayUtils;
import com.tyc.tdribbble.utils.ScreenUtils;
import com.tyc.tdribbble.utils.StringOauth;
import com.tyc.tdribbble.utils.TimeUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.Time;
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

    private static final int REQUEST_CODE = 102;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
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

    private LinearShotsAdapter adapter;
    private String token;
    private int count = 1;
    private  HashMap<String, String> hashMap = new HashMap<>();
    private HomePresenter  homePresenter;
    private boolean isFlag=false;
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
        mIvAvatar = headerLayout.findViewById(R.id.iv_avatar);
        mIvAvatar.setOnClickListener(this);
        mTvName = headerLayout.findViewById(R.id.tv_name);
        mTvName.setOnClickListener(this);
        mIvBg = headerLayout.findViewById(R.id.iv_bg);
        mSrlShots.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        int width= ScreenUtils.getScreenWidth(this);
        int height=DisplayUtils.dip2px(this,56);
        mSpTime.setDropDownWidth(width/3);//设置下拉菜单的宽度
        mSpTime.setDropDownHorizontalOffset(height);////设置选择微调的弹出窗口中像素的水平偏移。在mode_dropdown唯一有效的；
        mSpTime.setDropDownVerticalOffset(height);//设置选择微调的弹出窗口中像素的垂直偏移。在mode_dropdown唯一有效的；


        mSpList.setDropDownWidth(width/3);//设置下拉菜单的宽度
        mSpList.setDropDownHorizontalOffset(height);////设置选择微调的弹出窗口中像素的水平偏移。在mode_dropdown唯一有效的；
        mSpList.setDropDownVerticalOffset(height);//设置选择微调的弹出窗口中像素的垂直偏移。在mode_dropdown唯一有效的；


        mSpSort.setDropDownWidth(width/3);//设置下拉菜单的宽度
        mSpSort.setDropDownHorizontalOffset( height);////设置选择微调的弹出窗口中像素的水平偏移。在mode_dropdown唯一有效的；
        mSpSort.setDropDownVerticalOffset( height);//设置选择微调的弹出窗口中像素的垂直偏移。在mode_dropdown唯一有效的；



        homePresenter = new HomePresenter(this);
        mSpList.setOnItemSelectedListener(this);
        mSpSort.setOnItemSelectedListener(this);
        mSpTime.setOnItemSelectedListener(this);

        hashMap.put(ApiConstants.DATE, TimeUtils.dateToStr(TimeUtils.FORMAT_DATE, null));
        hashMap.put(ApiConstants.PAGE, String.valueOf(count));
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
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
                int lastItemPosition=linearLayoutManager.findLastVisibleItemPosition();
                int count=recyclerView.getAdapter().getItemCount();
                if((lastItemPosition==count-2||count<2)&&!isFlag)
                {
                    isFlag=true;
                    hashMap.remove(ApiConstants.PAGE);
                    count++;
                    hashMap.put(ApiConstants.PAGE, String.valueOf(count));
                    homePresenter.loadShots(HomeActivity.this,hashMap, 1);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            time = System.currentTimeMillis() - time;
            if (time > 1000) {
                Snackbar.make(drawer, R.string.str_finish_app, Snackbar.LENGTH_LONG).show();
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
            type=0;
            final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            linearLayoutManager.setAutoMeasureEnabled(true);
            mRvShots.setLayoutManager(linearLayoutManager);
            if(adapter!=null)
            {
                adapter.chageType(type);
            }
            return true;
        }
        if (id == R.id.action_small_image) {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
            mRvShots.setLayoutManager(gridLayoutManager);
            type=1;
            if(adapter!=null)
            {
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
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_shots) {
            // Handle the camera action
            if (TextUtils.isEmpty(token)) {
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, LoginActivity.class);
                intent.putExtra("url", StringOauth.getOauthSting());
                startActivityForResult(intent, REQUEST_CODE);
            }else{
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, MyUserShotsActivity.class);
                intent.putExtra(ApiConstants.USERID,String.valueOf(userEntity.getId()));
                startActivity(intent);
            }

        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent();
            intent.setClass(this, AboutActivity.class);
            startActivity(intent);
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
                    startActivityForResult(intent, REQUEST_CODE);
                    view.setEnabled(false);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this, UserActivity.class);
                    intent.putExtra(ApiConstants.USER, userEntity);
                    startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                            Pair.create((View) mIvAvatar, getResources().getString(R.string.str_avatar_tran)),
                            Pair.create((View) mTvName, getResources().getString(R.string.str_name_tran))).toBundle());
                }
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(UserEntity userEntity) {
        this.userEntity = userEntity;
        Glide.with(this).load(userEntity.getAvatarUrl()).error(R.mipmap.ic_default_avatar).into(mIvAvatar);
        Glide.with(this).load(userEntity.getAvatarUrl()).bitmapTransform(new BlurTransformation(this, 18, 3)).into(mIvBg);
        mTvName.setText(userEntity.getName());
        mIvAvatar.setEnabled(true);
    }


    @Override
    public void showShots(List<ShotsEntity> shotsEntities) {
        if(mRvShots.getAdapter()==null){
            adapter=new LinearShotsAdapter(this, shotsEntities,type);
            mRvShots.setAdapter(adapter);
        }else{
            ((LinearShotsAdapter) mRvShots.getAdapter()).swipeData(shotsEntities);
        }
        mSrlShots.setRefreshing(false);
    }

    @Override
    public void loadMoreShots(List<ShotsEntity> shotsEntities) {
        if(mRvShots.getAdapter()==null){
            adapter=new LinearShotsAdapter(this, shotsEntities,type);
            mRvShots.setAdapter(adapter);
        }else{
            ((LinearShotsAdapter) mRvShots.getAdapter()).addData(shotsEntities);
        }
        isFlag=false;

    }

    @Override
    public void showUser(UserEntity userEntity) {
        this.userEntity = userEntity;
        Glide.with(this).load(userEntity.getAvatarUrl()).error(R.mipmap.ic_default_avatar).into(mIvAvatar);
        Glide.with(this).load(userEntity.getAvatarUrl()).bitmapTransform(new BlurTransformation(this, 18, 3)).into(mIvBg);
        mTvName.setText(userEntity.getName());
        mIvAvatar.setEnabled(true);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onRefresh() {
        homePresenter.loadShots(this,hashMap, 0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch (adapterView.getId())
            {
                case R.id.sp_list:
                    hashMap.put("list", ApiConstants.Shots.LIST_VALUES[i]);
                    break;
                case R.id.sp_sort:
                    hashMap.put("sort",ApiConstants.Shots.SORT_VALUES[i]);
                    break;
                case R.id.sp_time:
                    hashMap.put("timeframe", ApiConstants.Shots.TIME_VALUES[i]);
                    break;
            }
//        TextView tv = (TextView)view;
//        tv.setGravity(Gravity.CENTER);
//
        homePresenter.loadShots(this,hashMap, 0);
        mSrlShots.setRefreshing(true);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
//        for(int j=0;j<adapterView.getCount();j++)
//        {
//            TextView itemTv = (TextView) adapterView.getChildAt(j);
//            itemTv.setGravity(Gravity.CENTER);
//        }
    }

    /**
     * 为 DrawerLayout 布局设置状态栏透明
     *
     * @param activity     需要设置的activity
     * @param drawerLayout DrawerLayout
     */
    public static void setTranslucentForDrawerLayout(Activity activity, DrawerLayout drawerLayout) {
        // 设置状态栏透明
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 设置内容布局属性
        ViewGroup contentLayout = (ViewGroup) drawerLayout.getChildAt(0);
        contentLayout.setFitsSystemWindows(true);
        contentLayout.setClipToPadding(true);
        // 设置抽屉布局属性
        ViewGroup vg = (ViewGroup) drawerLayout.getChildAt(1);
        vg.setFitsSystemWindows(false);
        // 设置 DrawerLayout 属性
        drawerLayout.setFitsSystemWindows(false);
    }
}
