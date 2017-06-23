package com.tyc.tdribbble.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.tyc.tdribbble.ui.login.LoginActivity;
import com.tyc.tdribbble.utils.DisplayUtils;
import com.tyc.tdribbble.utils.ScreenUtils;
import com.tyc.tdribbble.utils.StringOauth;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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
    private int count = 0;
    private  HashMap<String, String> hashMap = new HashMap<>();
    private HomePresenter  homePresenter;
    private boolean isFlag=false;
    private int type=0;
    @Override
    protected int layoutResID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        token = TDribbbleApp.token;
        setSupportActionBar(mToolbar);
        View headerLayout = mNavView.getHeaderView(0);
        mIvAvatar = headerLayout.findViewById(R.id.iv_avatar);
        mIvAvatar.setOnClickListener(this);
        mTvName = headerLayout.findViewById(R.id.tv_name);
        mTvName.setOnClickListener(this);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

//        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource ( this , R.array.time , R.layout.item_spinnerlayout );
//        mSpTime.setAdapter(timeAdapter);

        mSpList.setDropDownWidth(width/3);//设置下拉菜单的宽度
        mSpList.setDropDownHorizontalOffset(height);////设置选择微调的弹出窗口中像素的水平偏移。在mode_dropdown唯一有效的；
        mSpList.setDropDownVerticalOffset(height);//设置选择微调的弹出窗口中像素的垂直偏移。在mode_dropdown唯一有效的；

//        ArrayAdapter<CharSequence> listAdapter = ArrayAdapter.createFromResource ( this , R.array.list, R.layout.item_spinnerlayout );
//        mSpList.setAdapter(listAdapter);

        mSpSort.setDropDownWidth(width/3);//设置下拉菜单的宽度
        Log.i("debug",mSpSort.getHeight()+"-----");
        mSpSort.setDropDownHorizontalOffset( height);////设置选择微调的弹出窗口中像素的水平偏移。在mode_dropdown唯一有效的；
        mSpSort.setDropDownVerticalOffset( height);//设置选择微调的弹出窗口中像素的垂直偏移。在mode_dropdown唯一有效的；

//        ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource ( this , R.array.sort , R.layout.item_spinnerlayout );
//        mSpSort.setAdapter(sortAdapter);

        homePresenter = new HomePresenter(this);
        mSpList.setOnItemSelectedListener(this);
        mSpSort.setOnItemSelectedListener(this);
        mSpTime.setOnItemSelectedListener(this);

        hashMap.put("date", "2017-06-22");
        hashMap.put("page", String.valueOf(count));
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
                    hashMap.remove("page");
                    count++;
                    hashMap.put("page",String.valueOf(count));
                    homePresenter.loadShots(hashMap, TDribbbleApp.token, 1);
                    Log.i("debug","-----------");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:

                if (TextUtils.isEmpty(token)) {
                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this, LoginActivity.class);
                    intent.putExtra("url", StringOauth.getOauthSting());
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    //Log
                }
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(UserEntity userEntity) {
        Log.i("debug", userEntity.getAvatarUrl());
        Glide.with(this).load(userEntity.getAvatarUrl()).asGif().placeholder(R.mipmap.ic_avatar).into(mIvAvatar);
        mTvName.setText(userEntity.getName());
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
        homePresenter.loadShots(hashMap, token, 0);
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
        TextView tv = (TextView)view;
        tv.setGravity(Gravity.CENTER);
//
        homePresenter.loadShots(hashMap, TDribbbleApp.token, 0);
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
}
