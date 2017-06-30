package com.tyc.tdribbble.ui.shotsdetails.Comments;

import java.util.HashMap;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface ICommentsPresenter {

    public void loadComments(String shotId, HashMap<String, String> hashMap, String token);

    public void likeComment(String shotId, String commentId, String token);
}
