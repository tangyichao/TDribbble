package com.tyc.tdribbble.ui.user.followers;

import android.util.Log;

import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.TokenEntity;
import com.tyc.tdribbble.entity.UserEntity;
import com.tyc.tdribbble.ui.login.ILoginModel;
import com.tyc.tdribbble.ui.login.ILoginView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class FollowersModel implements IFollowersModel {

    private IFollowersView iFollowersView;

    public FollowersModel(IFollowersView iFollowersView) {
        this.iFollowersView = iFollowersView;
    }


    @Override
    public void loadFollowers(String userId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.getFollowers(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FollowersEntity>>() {
                    @Override
                    public void accept(@NonNull List<FollowersEntity> followersEntities) throws Exception {
                        iFollowersView.showFollowers(followersEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iFollowersView.showError();
                    }
                });
    }
}
