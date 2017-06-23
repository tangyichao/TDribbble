package com.tyc.tdribbble.ui.user.followers;

import android.util.Log;

import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.TokenEntity;
import com.tyc.tdribbble.entity.UserEntity;
import com.tyc.tdribbble.ui.login.ILoginModel;
import com.tyc.tdribbble.ui.login.ILoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class FollowersModel implements ILoginModel {

    private ILoginView iLoginView;

    public FollowersModel(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void loadToken(String code) {
        ApiService service = ApiManager.getRetrofit(ApiConstants.BASE_URL).create(ApiService.class);
        service.getToKen(ApiConstants.OAuth.CLIENT_ID, ApiConstants.OAuth.CLIENT_SECRET, ApiConstants.OAuth.REDIRECT_URI, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TokenEntity>() {
                    @Override
                    public void accept(@NonNull TokenEntity tokenEntity) throws Exception {
                        if (tokenEntity.getAccessToken() != null) {
                            iLoginView.showToken(tokenEntity.getAccessToken());

                        } else {
                            iLoginView.showError();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i("debug", throwable.getMessage());
                        iLoginView.showError();
                    }
                });
    }

    @Override
    public void loadUser(String token) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1, token).create(ApiService.class);
        service.getUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserEntity>() {
                    @Override
                    public void accept(@NonNull UserEntity userEntity) throws Exception {
                        if (userEntity != null) {
                            iLoginView.showUser(userEntity);
                        } else {
                            iLoginView.showError();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iLoginView.showError();
                        Log.i("debug", throwable.getMessage());
                    }
                });
    }
}
