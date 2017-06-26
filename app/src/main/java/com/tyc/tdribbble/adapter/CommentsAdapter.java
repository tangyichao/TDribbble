package com.tyc.tdribbble.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.ui.user.UserActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：tangyc on 2017/6/26
 * 邮箱：874500641@qq.com
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.FollowersViewHolder> {

    private Context context;
    private List<CommentsEntity> commentsEntities;

    public CommentsAdapter(Context context, List<CommentsEntity> commentsEntities) {
        this.context = context;
        this.commentsEntities = commentsEntities;
    }


    @Override
    public FollowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_followers, parent, false);
        return new FollowersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FollowersViewHolder holder, final int position) {
        Glide.with(context).load(commentsEntities.get(position).getUser().getAvatarUrl()).placeholder(R.drawable.bg_default_avatar).error(R.drawable.bg_default_avatar).into(holder.mTvAvatar);
        String location = commentsEntities.get(position).getUser().getLocation();
        if (!TextUtils.isEmpty(location))
            holder.mTvLocation.setText(location);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "ok", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(context, UserActivity.class);
                intent.putExtra("user", commentsEntities.get(position).getUser());
                context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation((FragmentActivity) context,
                        Pair.create((View) holder.mTvAvatar, context.getResources().getString(R.string.str_avatar_tran)),
                        Pair.create((View) holder.mTvName, context.getResources().getString(R.string.str_name_tran))).toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentsEntities.size();
    }

    public class FollowersViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        CircleImageView mTvAvatar;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_location)
        TextView mTvLocation;

        public FollowersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
