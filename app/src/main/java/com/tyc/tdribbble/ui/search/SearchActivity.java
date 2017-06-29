package com.tyc.tdribbble.ui.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.adapter.LinearShotsAdapter;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.base.BaseActivity;
import com.tyc.tdribbble.entity.ShotsEntity;

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
public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener {
    @BindView(R.id.sv_search)
    SearchView mSvSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_search)
    RecyclerView mRvSearch;
    Map<String, String> map = new HashMap<>();

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
        mSvSearch.setIconified(false);
        mSvSearch.setOnQueryTextListener(this);

        SearchView.SearchAutoComplete mComplete = mSvSearch.findViewById(R.id.search_src_text);
        if (!TextUtils.isEmpty(search)) {
            mComplete.setText(search);
        }
        map.put("page", "1");
        map.put("per_page", "12");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        map.put("q", query);
        ApiService service = ApiManager.getRetrofitJsoup(ApiConstants.BASE_URL).create(ApiService.class);
        service.getSearch(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ShotsEntity>>() {
                    @Override
                    public void accept(@NonNull List<ShotsEntity> shotsEntities) throws Exception {
                        if (shotsEntities.size() > 0) {
                            Log.i("debug", "" + shotsEntities.size());
                            LinearLayoutManager manager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
                            mRvSearch.setLayoutManager(manager);
                            new LinearSnapHelper().attachToRecyclerView(mRvSearch);
                            mRvSearch.setAdapter(new LinearShotsAdapter(SearchActivity.this, shotsEntities, 0));
                        } else {
                            Log.i("debug", "" + shotsEntities.size() + " is 0");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i("debug", throwable.getMessage());
                    }
                });
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
