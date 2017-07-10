package com.tyc.tdribbble.ui.shotsdetails.Comments;

import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.HashMap;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface ICommentsModel {

    public void loadComments(RxFragment rxFragment,String shotId, HashMap<String, String> hashMap);

    public void likeComment(RxFragment rxFragment,String shotId, String commentId);
}
