package com.tyc.tdribbble.ui.shotsdetails;

import android.util.Log;

import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.TTEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class ShotsDetailsModel implements IShotsDetailsModel {

    private IShotsDetailsView iShotsDetails;

    public ShotsDetailsModel(IShotsDetailsView iShotsDetails) {
        this.iShotsDetails = iShotsDetails;
    }


    @Override
    public void likeShot(String shotId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.getLikeShot(shotId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TTEntity>() {
                    @Override
                    public void accept(@NonNull TTEntity ttentity) throws Exception {
                        iShotsDetails.likeShot(ttentity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i("debug", throwable.getMessage());
                        iShotsDetails.showError();
                    }
                });
    }

    @Override
    public void unlikeShot(String shotId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.unLikeShot(shotId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TTEntity>() {
                    @Override
                    public void accept(@NonNull TTEntity ttentity) throws Exception {
                        iShotsDetails.unlikeShot(ttentity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i("debug", throwable.getMessage());
                        iShotsDetails.showError();
                    }
                });
    }

    @Override
    public void checklikeShot(String shotId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.checkLikeShot(shotId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TTEntity>() {
                    @Override
                    public void accept(@NonNull TTEntity ttentity) throws Exception {
                        iShotsDetails.checklikeShot(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i("debug", throwable.getMessage());
                        iShotsDetails.checklikeShot(false);
                    }
                });
    }
}
