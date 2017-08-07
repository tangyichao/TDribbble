package com.tyc.tdribbble.ui.search;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.LinearShotsAdapter;
import com.tyc.tdribbble.adapter.SearchWordAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.ShotsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：tangyc on 2017/6/29
 * 邮箱：874500641@qq.com
 */
public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.sv_search)
    SearchView mSvSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_search)
    RecyclerView mRvSearch;
    Map<String, String> map = new HashMap<>();
    @BindView(R.id.srl_search)
    SwipeRefreshLayout mSrlSearch;
    @BindView(R.id.rv_search_word)
    RecyclerView mRvSearchWord;
    List<String> list = new ArrayList<>();
    SearchWordAdapter searchWordAdapter;
    @Override
    protected int layoutResID() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        String search = getIntent().getStringExtra("search");
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SearchView.SearchAutoComplete mComplete = (SearchView.SearchAutoComplete) mSvSearch.findViewById(R.id.search_src_text);
        if (!TextUtils.isEmpty(search)) {
            mComplete.setText(search);
        }
        mSvSearch.setIconified(false);
        mSvSearch.setOnQueryTextListener(this);
        mSvSearch.requestFocus();
        map.put(ApiConstants.PAGE, "1");
        map.put(ApiConstants.PERPAGE, "12");
        mSrlSearch.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        mSrlSearch.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
        mRvSearchWord.setLayoutManager(manager);


        searchWordAdapter = new SearchWordAdapter(this, list);
        mRvSearchWord.setAdapter(searchWordAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        list.add(query);
        searchWordAdapter.notifyDataSetChanged();
        if (TextUtils.isEmpty(query)) {
            Toast.makeText(this, "请输入内容在搜索", Toast.LENGTH_LONG).show();
        } else {
            map.put("q", query);
            mSrlSearch.setRefreshing(true);
            ApiService service = ApiManager.getRetrofitJsoup(ApiConstants.BASE_URL).create(ApiService.class);
            service.getSearch(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<ShotsEntity>>() {
                        @Override
                        public void accept(@NonNull List<ShotsEntity> shotsEntities) throws Exception {
                            if (shotsEntities.size() > 0) {
                                LinearLayoutManager manager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
                                mRvSearch.setLayoutManager(manager);
                                //new LinearSnapHelper().attachToRecyclerView(mRvSearch);
                                mRvSearch.setAdapter(new LinearShotsAdapter(SearchActivity.this, shotsEntities, 5));
                            } else {
                                Log.i("debug", "" + shotsEntities.size() + " is 0");
                            }
                            mSrlSearch.setRefreshing(false);

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            mSrlSearch.setRefreshing(false);
                        }
                    });
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i("debug", newText);
        return false;
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
    public void onRefresh() {
    }
}
