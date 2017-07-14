package com.tyc.tdribbble.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.ui.home.HomeActivity;
import com.tyc.tdribbble.ui.shotsdetails.ShotsDetailsActivity;
import com.tyc.tdribbble.ui.user.UserActivity;
import com.tyc.tdribbble.utils.DisplayUtils;
import com.tyc.tdribbble.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：tangyc on 2017/6/22
 * 邮箱：874500641@qq.com
 */
public class LinearShotsAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ShotsEntity> shotsEntities;
    private int type;
    public LinearShotsAdapter(Context context, List<ShotsEntity> shotsEntities, int type) {
        this.context=context;
        this.shotsEntities=shotsEntities;
        this.type=type;
    }
     public void swipeData(List<ShotsEntity> shotsEntities){
         this.shotsEntities.clear();
         this.shotsEntities=shotsEntities;
         notifyDataSetChanged();
     }
    public void addData(List<ShotsEntity> shotsEntities){
        this.shotsEntities.addAll(shotsEntities);
        notifyDataSetChanged();
    }
    public void chageType(int type){
        this.type=type;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_linear_shots,parent,false);
        return new LinearShotsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int width = ScreenUtils.getScreenWidth(context) - DisplayUtils.dip2px(context, 16f);
        if (type == 1 || type == 3)
        {
            width = (width - DisplayUtils.dip2px(context, 16f)) / 2;
        }
        if (type == 4) {
            width = (width - DisplayUtils.dip2px(context, 16f)) * 2 / 3;
        }
        int height = width * 3 / 4;
        ViewGroup.LayoutParams params = ((LinearShotsViewHolder) (holder)).mIvShots.getLayoutParams();
        params.height = height;
        params.width = width;
        ((LinearShotsViewHolder) (holder)).mIvShots.setLayoutParams(params);

        if (type == 2) {
            ((LinearShotsViewHolder) (holder)).mLlAUthor.setVisibility(View.VISIBLE);
            String avatar = shotsEntities.get(position).getUser().getAvatarUrl();
            Glide.with(context).load(avatar).into(((LinearShotsViewHolder) holder).mIvAvatar);
            ((LinearShotsViewHolder) holder).mIvAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(context, UserActivity.class);
                    intent.putExtra(ApiConstants.USER, shotsEntities.get(holder.getAdapterPosition()).getUser());
                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((HomeActivity) context,
                            Pair.create(view, context.getResources().getString(R.string.str_avatar_tran)),
                            Pair.create((View) ((LinearShotsViewHolder) holder).mTvName, context.getResources().getString(R.string.str_name_tran))).toBundle());
                }
            });

            String name = shotsEntities.get(position).getUser().getName();
            ((LinearShotsViewHolder) holder).mTvName.setText(name);
            String location = shotsEntities.get(position).getUser().getLocation();
            if (TextUtils.isEmpty(location)) {
                ((LinearShotsViewHolder) holder).mTvLocation.setVisibility(View.GONE);
            } else {
                ((LinearShotsViewHolder) holder).mTvLocation.setText(location);
            }
            String title = shotsEntities.get(position).getTitle();
            ((LinearShotsViewHolder) holder).mTvTitle.setText(title);
            ((LinearShotsViewHolder) (holder)).mLlCounts.setVisibility(View.VISIBLE);
            int attachments = shotsEntities.get(position).getAttachmentsCount();
            if (attachments > 0) {
                ((LinearShotsViewHolder) holder).mTvAttachmentsCount.setText(String.valueOf(attachments));
            } else {
                ((LinearShotsViewHolder) holder).mTvAttachmentsCount.setVisibility(View.GONE);
            }
            ((LinearShotsViewHolder) holder).mTvAttachmentsCount.setText(String.valueOf(attachments));
            int likesCount = shotsEntities.get(position).getLikesCount();
            ((LinearShotsViewHolder) holder).mTvLikesCount.setText(String.valueOf(likesCount));
            int commentsCount = shotsEntities.get(position).getCommentsCount();
            ((LinearShotsViewHolder) holder).mTvCommentsCount.setText(String.valueOf(commentsCount));
            int viewsCount = shotsEntities.get(position).getViewsCount();
            ((LinearShotsViewHolder) holder).mTvViewsCount.setText(String.valueOf(viewsCount));
        } else {
            ((LinearShotsViewHolder) (holder)).mLlAUthor.setVisibility(View.GONE);
            ((LinearShotsViewHolder) (holder)).mLlCounts.setVisibility(View.GONE);

        }
        final boolean isAnimated = shotsEntities.get(position).isAnimated();
        if (isAnimated) {
            String hidpi=shotsEntities.get(position).getImages().getHidpi();
            Glide.with(context).load(hidpi).asGif().placeholder(R.drawable.bg_linear_shots).override(width, height).diskCacheStrategy(DiskCacheStrategy.SOURCE).dontTransform().fitCenter().into(((LinearShotsViewHolder) holder).mIvShots);
            if (type == 0 || type == 2 || type == 5) {
                 ((LinearShotsViewHolder) holder).mIvIsGif.setImageResource(R.mipmap.ic_gif);
           }else{
                ((LinearShotsViewHolder) holder).mIvIsGif.setImageResource(R.mipmap.ic_gif_small);
           }
            ((LinearShotsViewHolder) holder).mIvIsGif.setVisibility(View.VISIBLE);
        }else{
            String normal=shotsEntities.get(position).getImages().getNormal();
            Glide.with(context).load(normal).placeholder(R.drawable.bg_linear_shots).override(width,height).into(((LinearShotsViewHolder) holder).mIvShots);
            ((LinearShotsViewHolder) holder).mIvIsGif.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, ShotsDetailsActivity.class);
                intent.putExtra("type", type);
                intent.putExtra(ApiConstants.SHOTS, shotsEntities.get(holder.getAdapterPosition()));
                if (!isAnimated) {
                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            Pair.create((View) ((LinearShotsViewHolder) holder).mIvShots, context.getResources().getString(R.string.str_shots_tran))).toBundle());
                } else {
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return shotsEntities.size();
    }
     class LinearShotsViewHolder extends  RecyclerView.ViewHolder{
        @BindView(R.id.iv_shots)
        ImageView mIvShots;
         @BindView(R.id.iv_is_gif)
         ImageView mIvIsGif;
         @BindView(R.id.iv_avatar)
         CircleImageView mIvAvatar;
         @BindView(R.id.ll_author)
         LinearLayout mLlAUthor;
         @BindView(R.id.tv_name)
         TextView mTvName;
         @BindView(R.id.tv_location)
         TextView mTvLocation;
         @BindView(R.id.tv_title)
         TextView mTvTitle;
         @BindView(R.id.ll_counts)
         LinearLayout mLlCounts;
         @BindView(R.id.tv_attachments_count)
         TextView mTvAttachmentsCount;
         @BindView(R.id.tv_likes_count)
         TextView mTvLikesCount;
         @BindView(R.id.tv_comments_count)
         TextView mTvCommentsCount;
         @BindView(R.id.tv_views_count)
         TextView mTvViewsCount;
        public LinearShotsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

     }
}
