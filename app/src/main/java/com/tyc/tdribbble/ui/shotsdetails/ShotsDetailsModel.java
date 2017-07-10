package com.tyc.tdribbble.ui.shotsdetails;

import android.util.Log;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.ShotsEntity;
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
    public void likeShot(RxAppCompatActivity rxAppCompatActivity, String shotId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.getLikeShot(shotId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxAppCompatActivity.<TTEntity>bindToLifecycle())
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
    public void unlikeShot(RxAppCompatActivity rxAppCompatActivity,String shotId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.unLikeShot(shotId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxAppCompatActivity.<TTEntity>bindToLifecycle())
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
    public void checklikeShot(RxAppCompatActivity rxAppCompatActivity,String shotId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.checkLikeShot(shotId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.compose(this.<Long>bindToLifecycle())
                .compose(rxAppCompatActivity.<TTEntity>bindToLifecycle())
                .subscribe(new Consumer<TTEntity>() {
                    @Override
                    public void accept(@NonNull TTEntity ttentity) throws Exception {
                        iShotsDetails.checklikeShot(true);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iShotsDetails.checklikeShot(false);
                    }
                });
    }

    @Override
    public void loadComments(RxAppCompatActivity rxAppCompatActivity, String shotId, final HashMap<String, String> hashMap) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.getComments(shotId, hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxAppCompatActivity.<List<CommentsEntity>>bindToLifecycle())
                .subscribe(new Consumer<List<CommentsEntity>>() {
                    @Override
                    public void accept(@NonNull List<CommentsEntity> commentsEntities) throws Exception {
                        if (commentsEntities.size() > 0 || Integer.valueOf(hashMap.get(ApiConstants.PAGE)) > 1) {
                            iShotsDetails.showComments(commentsEntities);
                        } else {
                            //iShotsDetails.showCommentsError();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iShotsDetails.showCommentsError();
                    }
                });
    }

    @Override
    public void likeComment(RxAppCompatActivity rxAppCompatActivity, String shotId, String commentId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.getLikeComment(shotId, commentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxAppCompatActivity.<TTEntity>bindToLifecycle())
                .subscribe(new Consumer<TTEntity>() {
                    @Override
                    public void accept(@NonNull TTEntity ttentity) throws Exception {
                        iShotsDetails.likeComment(ttentity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i("debug", throwable.getMessage());
                    }
                });
    }

    @Override
    public void loadShotsIntroduction(RxAppCompatActivity rxAppCompatActivity, String shotId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.getShot(shotId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxAppCompatActivity.<ShotsEntity>bindToLifecycle())
                .subscribe(new Consumer<ShotsEntity>() {
                    @Override
                    public void accept(@NonNull ShotsEntity shotsEntity) throws Exception {
                        iShotsDetails.showShotsIntroduction(shotsEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iShotsDetails.showError();
                    }
                });
    }
}
