package com.tyc.tdribbble.ui.user.likes;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.LikesEntity;
import com.tyc.tdribbble.entity.ShotsEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class LikesModel implements ILikesModel {

    private ILikesView iShotsView;

    public LikesModel(ILikesView iShotsView) {
        this.iShotsView = iShotsView;
    }


    @Override
    public void loadShots(RxFragment rxFragment, String userId, int shotsType) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        Observable<List<LikesEntity>> listObservable = service.getUserLikes(userId);

        listObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxFragment.<List<LikesEntity>>bindToLifecycle())
                .subscribe(new Consumer<List<LikesEntity>>() {
                    @Override
                    public void accept(@NonNull List<LikesEntity> shotsEntities) throws Exception {
                        iShotsView.showLikes(shotsEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iShotsView.showError();
                    }
                });
    }
}
