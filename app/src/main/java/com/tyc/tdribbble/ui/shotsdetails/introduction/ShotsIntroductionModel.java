package com.tyc.tdribbble.ui.shotsdetails.introduction;

import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
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
public class ShotsIntroductionModel implements IShotsIntroductionModel {

    private IShotsIntroductionView iShotsView;

    public ShotsIntroductionModel(IShotsIntroductionView iShotsView) {
        this.iShotsView = iShotsView;
    }


    @Override
    public void loadShotsIntroduction(String shotId, String token) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1, token).create(ApiService.class);
        service.getShot(shotId, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShotsEntity>() {
                    @Override
                    public void accept(@NonNull ShotsEntity shotsEntity) throws Exception {
                        iShotsView.showShotsIntroduction(shotsEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iShotsView.showError();
                    }
                });
    }
}
