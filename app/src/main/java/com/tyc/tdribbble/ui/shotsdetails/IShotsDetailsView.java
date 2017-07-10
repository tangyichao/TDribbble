package com.tyc.tdribbble.ui.shotsdetails;

import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.TTEntity;

import java.util.List;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IShotsDetailsView {
    public void likeShot(TTEntity ttEntity);

    public void unlikeShot(TTEntity ttEntity);

    public void checklikeShot(boolean isLike);

    public void showError();

    public void showComments(List<CommentsEntity> commentsEntities);

    public void likeComment(TTEntity ttEntity);
    public void showEmpty();
    public void showCommentsError();
    public void showShotsIntroduction(ShotsEntity shotsEntity);

}
