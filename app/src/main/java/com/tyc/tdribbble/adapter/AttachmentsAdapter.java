package com.tyc.tdribbble.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.entity.AttachmentsEntity;
import com.tyc.tdribbble.utils.FileUtils;
import com.tyc.tdribbble.utils.TimeUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：tangyc on 2017/6/30
 * 邮箱：874500641@qq.com
 */
public class AttachmentsAdapter extends RecyclerView.Adapter<AttachmentsAdapter.AttachmentsViewHolder> {

    private Context context;
    private List<AttachmentsEntity> attachmentsEntities;

    public AttachmentsAdapter(Context context, List<AttachmentsEntity> attachmentsEntities) {
        this.context = context;
        this.attachmentsEntities = attachmentsEntities;
    }

    @Override
    public AttachmentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_attachments, parent, false);
        return new AttachmentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttachmentsViewHolder holder, int position) {
        final AttachmentsEntity attachmentsEntity = attachmentsEntities.get(position);
        Glide.with(context).load(attachmentsEntity.getThumbnail_url()).into(holder.mIvThumbnail);
        holder.mTvTime.setText(TimeUtils.getTimeFromISO8601(attachmentsEntity.getCreated_at()) + "创建");
        holder.mTvContentType.setText("附件类型" + attachmentsEntity.getContent_type());
        holder.mTvSize.setText("附件大小" + FileUtils.getPrintSize(attachmentsEntity.getSize()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = attachmentsEntity.getUrl();
                // 创建下载请求
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

/*
 * 设置在通知栏是否显示下载通知(下载进度), 有 3 个值可选:
 *    VISIBILITY_VISIBLE:                   下载过程中可见, 下载完后自动消失 (默认)
 *    VISIBILITY_VISIBLE_NOTIFY_COMPLETED:  下载过程中和下载完成后均可见
 *    VISIBILITY_HIDDEN:                    始终不显示通知
 */
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

// 设置通知的标题和描述
                request.setTitle("附件下载");
                request.setDescription("");

/*
 * 设置允许使用的网络类型, 可选值:
 *     NETWORK_MOBILE:      移动网络
 *     NETWORK_WIFI:        WIFI网络
 *     NETWORK_BLUETOOTH:   蓝牙网络
 * 默认为所有网络都允许
 */
// request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

// 添加请求头
// request.addRequestHeader("User-Agent", "Chrome Mozilla/5.0");

// 设置下载文件的保存位置
                File saveFile = new File(Environment.getExternalStorageDirectory(), "demo.jpg");
                request.setDestinationUri(Uri.fromFile(saveFile));

/*
 * 2. 获取下载管理器服务的实例, 添加下载任务
 */
                DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

// 将下载请求加入下载队列, 返回一个下载ID
                long downloadId = manager.enqueue(request);
            }
        });
    }

    @Override
    public int getItemCount() {
        return attachmentsEntities.size();
    }

    public class AttachmentsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_thumbnail)
        ImageView mIvThumbnail;
        @BindView(R.id.tv_size)
        TextView mTvSize;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_content_type)
        TextView mTvContentType;

        public AttachmentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
