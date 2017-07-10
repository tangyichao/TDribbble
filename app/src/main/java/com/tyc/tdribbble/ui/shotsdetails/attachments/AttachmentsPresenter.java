package com.tyc.tdribbble.ui.shotsdetails.attachments;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class AttachmentsPresenter implements IAttachmentsPresenter {
    private IAttachmentsView iAttachmentsView;
    private AttachmentsModel model;

    public AttachmentsPresenter(IAttachmentsView iAttachmentsView) {
        this.iAttachmentsView = iAttachmentsView;
        model = new AttachmentsModel(iAttachmentsView);
    }

    @Override
    public void loadAttachments(RxAppCompatActivity rxAppCompatActivity,String shotId) {
        model.loadAttachments(rxAppCompatActivity,shotId);
    }

}
