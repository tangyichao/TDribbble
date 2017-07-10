package com.tyc.tdribbble.ui.user.shots;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.entity.ShotsEntity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class ShotsModel implements IShotsModel {

    private IShotsView iShotsView;

    public ShotsModel(IShotsView iShotsView) {
        this.iShotsView = iShotsView;
    }


    @Override
    public void loadShots(RxFragment rxFragment,String userId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.getUserShots(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxFragment.<List<ShotsEntity>>bindToLifecycle())
                .subscribe(new Consumer<List<ShotsEntity>>() {
                    @Override
                    public void accept(@NonNull List<ShotsEntity> shotsEntities) throws Exception {
                        if (shotsEntities.size() > 0) {
                            iShotsView.showShots(shotsEntities);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iShotsView.showError();
                    }
                });
    }
}
