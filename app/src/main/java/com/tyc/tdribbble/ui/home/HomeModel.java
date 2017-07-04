package com.tyc.tdribbble.ui.home;

import android.util.Log;

import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.UserEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class HomeModel implements IHomeModel {
    private IHomeView iHomeView;
    public HomeModel(IHomeView iHomeView) {
        this.iHomeView=iHomeView;
    }

    @Override
    public void loadShots(Map<String, String> map, String token, final int type) {
        {
            ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1,token).create(ApiService.class);
            service.getShots(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<ShotsEntity>>() {
                        @Override
                        public void accept(@NonNull List<ShotsEntity> shotsEntities) throws Exception {
                            if(shotsEntities.size()>0)
                            {

                                if (type == 0) {
                                    iHomeView.showShots(shotsEntities);
                                }else{
                                    iHomeView.loadMoreShots(shotsEntities);
                                }
                            }else{
                                iHomeView.showEmpty();
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                                iHomeView.showError();
                        }
                    });
        }
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
                            iHomeView.showUser(userEntity);
                        } else {
                            //iLoginView.showError();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        //iLoginView.showError();
                        Log.i("debug", throwable.getMessage());
                    }
                });
    }
}
