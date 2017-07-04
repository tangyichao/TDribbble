package com.tyc.tdribbble.ui.shotsdetails.Comments;

import android.util.Log;

import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.api.ApiManager;
import com.tyc.tdribbble.api.ApiService;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.FollowersEntity;
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
public class CommentsModel implements ICommentsModel {

    private ICommentsView iCommentsView;

    public CommentsModel(ICommentsView iCommentsView) {
        this.iCommentsView = iCommentsView;
    }


    @Override
    public void loadComments(String shotId, final HashMap<String, String> hashMap, String token) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1, token).create(ApiService.class);
        service.getComments(shotId, hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CommentsEntity>>() {
                    @Override
                    public void accept(@NonNull List<CommentsEntity> commentsEntities) throws Exception {
                        if (commentsEntities.size() > 0 || Integer.valueOf(hashMap.get(ApiConstants.PAGE)) > 1) {
                            iCommentsView.showComments(commentsEntities);
                        } else {
                            iCommentsView.showEmpty();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iCommentsView.showError();
                    }
                });
    }

    @Override
    public void likeComment(String shotId, String commentId, String token) {
        ApiService service = ApiManager.getRetrofitUser(ApiConstants.BASE_URL_V1, token).create(ApiService.class);
        service.getLikeComment(shotId, commentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TTEntity>() {
                    @Override
                    public void accept(@NonNull TTEntity ttentity) throws Exception {
                        iCommentsView.likeComment(ttentity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i("debug", throwable.getMessage());
                    }
                });
    }
}
