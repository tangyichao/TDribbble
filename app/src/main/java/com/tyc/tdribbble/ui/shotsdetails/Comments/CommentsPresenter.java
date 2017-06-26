package com.tyc.tdribbble.ui.shotsdetails.Comments;

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
    public void loadComments(String shotId, String token) {
        model.loadComments(shotId, token);
    }

}
