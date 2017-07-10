package com.tyc.tdribbble.ui.shotsdetails.Comments;

import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.HashMap;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class CommentsPresenter implements ICommentsPresenter {
    private ICommentsView iCommentsView;
    private CommentsModel model;

    public CommentsPresenter(ICommentsView iCommentsView) {
        this.iCommentsView = iCommentsView;
        model = new CommentsModel(iCommentsView);
    }

    @Override
    public void loadComments(RxFragment rxFragment,String shotId, HashMap<String, String> hashMap) {
        model.loadComments(rxFragment,shotId, hashMap);
    }

    @Override
    public void likeComment(RxFragment rxFragment,String shotId, String commentId) {
        model.likeComment(rxFragment,shotId, commentId);
    }

}
