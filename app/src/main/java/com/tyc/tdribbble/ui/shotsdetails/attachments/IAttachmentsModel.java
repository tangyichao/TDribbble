package com.tyc.tdribbble.ui.shotsdetails.attachments;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface IAttachmentsModel {

    public void loadAttachments(RxAppCompatActivity rxAppCompatActivity,String shotId);

}
