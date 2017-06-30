package com.tyc.tdribbble.ui.shotsdetails;

import java.util.HashMap;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IShotsDetailsPresenter {

    public void likeShot(String shotId, String token);

    public void unlikeShot(String shotId, String token);

    public void checklikeShot(String shotId, String token);
}
