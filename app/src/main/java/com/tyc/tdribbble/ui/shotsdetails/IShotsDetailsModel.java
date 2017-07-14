package com.tyc.tdribbble.ui.shotsdetails;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.HashMap;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IShotsDetailsModel {

    public void likeShot(RxAppCompatActivity rxAppCompatActivity, String shotId);

    public void unlikeShot(RxAppCompatActivity rxAppCompatActivity,String shotId);

    public void checklikeShot(RxAppCompatActivity rxAppCompatActivity,String shotId);

    public void loadComments(RxAppCompatActivity rxAppCompatActivity, String shotId, HashMap<String, String> hashMap);

    public void likeComment(RxAppCompatActivity rxAppCompatActivity,String shotId, String commentId);

    public void loadShotsIntroduction(RxAppCompatActivity rxAppCompatActivity, String shotId);

    public void createComment(RxAppCompatActivity rxAppCompatActivity, String shotId, String body);
}
