package com.tyc.tdribbble.ui.shotsdetails.attachments;

import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.AttachmentsEntity;
import com.tyc.tdribbble.entity.FollowersEntity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class AttachmentsModel implements IAttachmentsModel {

    private IAttachmentsView iAttachmentsView;

    public AttachmentsModel(IAttachmentsView iAttachmentsView) {
        this.iAttachmentsView = iAttachmentsView;
    }


    @Override
    public void loadAttachments(String shotId) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1).create(ApiService.class);
        service.getAttachments(shotId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<AttachmentsEntity>>() {
                    @Override
                    public void accept(@NonNull List<AttachmentsEntity> attachmentsEntities) throws Exception {
                        iAttachmentsView.showAttachments(attachmentsEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iAttachmentsView.showError();
                    }
                });
    }
}
