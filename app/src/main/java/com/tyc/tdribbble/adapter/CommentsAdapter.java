package com.tyc.tdribbble.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.ui.user.UserActivity;
import com.tyc.tdribbble.utils.DisplayUtils;
import com.tyc.tdribbble.utils.HtmlFormatUtils;
import com.tyc.tdribbble.utils.ScreenUtils;
import com.tyc.tdribbble.utils.TimeUtils;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：tangyc on 2017/6/26
 * 邮箱：874500641@qq.com
 */
public class CommentsAdapter extends RecyclerView.Adapter {

    private static final int COMMENTSTYPE = 201;
    private static final int FOOTERTYPE = 202;
    private Context context;
    private List<CommentsEntity> commentsEntities;
    private boolean isFooter = false;
    private OnClickLikeListener listener;


    public void setListener(OnClickLikeListener listener) {
        this.listener = listener;
    }

    public CommentsAdapter(Context context, List<CommentsEntity> commentsEntities) {
        this.context = context;
        this.commentsEntities = commentsEntities;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == COMMENTSTYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_comments, parent, false);
            return new CommentsViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.foot_view, parent, false);
            return new FooterViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == COMMENTSTYPE) {
            Glide.with(context).load(commentsEntities.get(position).getUser().getAvatarUrl()).error(R.mipmap.ic_default_avatar).into(((CommentsViewHolder) holder).mIvAvatar);
            String location = commentsEntities.get(position).getUser().getLocation();
            if (!TextUtils.isEmpty(location))
                ((CommentsViewHolder) holder).mTvLocation.setText(location);
            else
                ((CommentsViewHolder) holder).mTvLocation.setVisibility(View.GONE);
            String name = commentsEntities.get(position).getUser().getName();
            if (!TextUtils.isEmpty(name))
                ((CommentsViewHolder) holder).mTvName.setText(name);
            String time = commentsEntities.get(position).getUpdated_at();
            ((CommentsViewHolder) holder).mTvTime.setText(TimeUtils.getTimeFromISO8601(time));
            String body = commentsEntities.get(position).getBody();
            if (!TextUtils.isEmpty(body))
                ((CommentsViewHolder) holder).mTvBody.setMovementMethod(LinkMovementMethod.getInstance());
            int likesCount = commentsEntities.get(position).getLikes_count();
            ((CommentsViewHolder) holder).mTvLikesCount.setText(String.valueOf(likesCount));
            HtmlFormatUtils.Html2StringNoP(((CommentsViewHolder) holder).mTvBody, body);
            ((CommentsViewHolder) holder).mIvAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(context, UserActivity.class);
                    intent.putExtra(ApiConstants.USER, commentsEntities.get(holder.getAdapterPosition()).getUser());
                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((FragmentActivity) context,
                            Pair.create((View) ((CommentsViewHolder) holder).mIvAvatar, context.getResources().getString(R.string.str_avatar_tran)),
                            Pair.create((View) ((CommentsViewHolder) holder).mTvName, context.getResources().getString(R.string.str_name_tran))).toBundle());
                }
            });
            ((CommentsViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((CommentsViewHolder) holder).mLLShowComment.setVisibility(View.INVISIBLE);
                    ((CommentsViewHolder) holder).mLLLikeComment.setVisibility(View.VISIBLE);
                }
            });
            ((CommentsViewHolder) holder).mTvLikesCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(commentsEntities.get(holder.getAdapterPosition()));
                }
            });
        } else {
            if (isFooter) {
                ((FooterViewHolder) holder).mLlLoading.setVisibility(View.GONE);
                ((FooterViewHolder) holder).mTvEnd.setVisibility(View.VISIBLE);
            } else {
                ((FooterViewHolder) holder).mLlLoading.setVisibility(View.VISIBLE);
                ((FooterViewHolder) holder).mTvEnd.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return commentsEntities.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position <= commentsEntities.size() - 1) {
            return COMMENTSTYPE;
        } else {
            return FOOTERTYPE;
        }
    }

    public void addData(List<CommentsEntity> commentsEntities) {
        this.commentsEntities.addAll(commentsEntities);
        isFooter = commentsEntities.size() < 20;
        notifyDataSetChanged();
    }

    public void swipeLike(long id) {
        for (int i = 0; i < commentsEntities.size(); i++) {
            if (commentsEntities.get(i).getId() == id) {
                notifyItemChanged(i);
            }
        }
        // notifyDataSetChanged();
    }
    public class CommentsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        CircleImageView mIvAvatar;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_location)
        TextView mTvLocation;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_body)
        TextView mTvBody;
        @BindView(R.id.tv_likes_count)
        TextView mTvLikesCount;
        @BindView(R.id.ll_like_comment)
        LinearLayout mLLLikeComment;
        @BindView(R.id.ll_show_comment)
        LinearLayout mLLShowComment;
        public CommentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_loading)
        LinearLayout mLlLoading;
        @BindView(R.id.tv_end)
        TextView mTvEnd;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickLikeListener {
        public void onClick(Object object);
    }
}
