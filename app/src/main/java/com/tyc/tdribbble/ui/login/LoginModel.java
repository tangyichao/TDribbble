package com.tyc.tdribbble.ui.login;

import android.util.Log;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.TokenEntity;
import com.tyc.tdribbble.entity.UserEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class LoginModel implements ILoginModel{

    private ILoginView iLoginView;
    public LoginModel(ILoginView iLoginView) {
        this.iLoginView=iLoginView;
    }

    @Override
    public void loadToken(RxAppCompatActivity rxAppCompatActivity, String code) {
        ApiService service = ApiManager.getRetrofit(ApiConstants.BASE_URL).create(ApiService.class);
        service.getToKen(ApiConstants.OAuth.CLIENT_ID, ApiConstants.OAuth.CLIENT_SECRET, ApiConstants.OAuth.REDIRECT_URI, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxAppCompatActivity.<TokenEntity>bindToLifecycle())
                .subscribe(new Consumer<TokenEntity>() {
                    @Override
                    public void accept(@NonNull TokenEntity tokenEntity) throws Exception {
                        if(tokenEntity.getAccessToken()!=null)
                        {
                            iLoginView.showToken(tokenEntity.getAccessToken());

                        }else {
                            iLoginView.showError();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i("debug",throwable.getMessage());
                        iLoginView.showError();
                    }
                });
    }

    @Override
    public void loadUser(RxAppCompatActivity rxAppCompatActivity) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxAppCompatActivity.<UserEntity>bindToLifecycle())
                .subscribe(new Consumer<UserEntity>() {
                    @Override
                    public void accept(@NonNull UserEntity userEntity) throws Exception {
                        if(userEntity!=null)
                        {
                            iLoginView.showUser(userEntity);
                        }else{
                            iLoginView.showError();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iLoginView.showError();
                    }
                });
    }
}
