package com.tyc.tdribbble.ui.shotsdetails.attachments;

import com.tyc.tdribbble.entity.AttachmentsEntity;
import com.tyc.tdribbble.entity.FollowersEntity;

import java.util.List;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IAttachmentsView {
    public void showAttachments(List<AttachmentsEntity> attachmentsEntities);

    public void showError();
}
