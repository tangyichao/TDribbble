package com.tyc.tdribbble.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.ui.user.UserActivity;
import com.tyc.tdribbble.utils.HtmlFormatUtils;
import com.tyc.tdribbble.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：tangyc on 2017/6/26
 * 邮箱：874500641@qq.com
 */
public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder> {

    private Context context;
    private List<FollowersEntity> followersEntities;

    public FollowersAdapter(Context context, List<FollowersEntity> followersEntities) {
        this.context = context;
        this.followersEntities = followersEntities;
    }


    @Override
    public FollowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_followers, parent, false);
        return new FollowersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FollowersViewHolder holder, int position) {
        Glide.with(context).load(followersEntities.get(position).getFollower().getAvatarUrl()).error(R.mipmap.ic_default_avatar).into(holder.mIvAvatar);
        String location = followersEntities.get(position).getFollower().getLocation();
        if (!TextUtils.isEmpty(location))
            holder.mTvLocation.setText(location);
        else
            holder.mTvLocation.setVisibility(View.GONE);
        holder.mTvName.setText(followersEntities.get(position).getFollower().getName());
        holder.mTvTime.setText(TimeUtils.getTimeFromISO8601(followersEntities.get(position).getCreated_at()) + "关注");
        String bio = followersEntities.get(position).getFollower().getBio();
        if (!TextUtils.isEmpty(bio))
            HtmlFormatUtils.Html2StringNoP(holder.mTvBio, bio);
        else
            holder.mTvBio.setVisibility(View.INVISIBLE);
        holder.mTvShotsCount.setText(followersEntities.get(position).getFollower().getShotsCount() + "作品");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, UserActivity.class);
                intent.putExtra(ApiConstants.USER, followersEntities.get(holder.getAdapterPosition()).getFollower());
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((FragmentActivity) context,
                        Pair.create((View) holder.mIvAvatar, context.getResources().getString(R.string.str_avatar_tran)),
                        Pair.create((View) holder.mTvName, context.getResources().getString(R.string.str_name_tran))).toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return followersEntities.size();
    }

    public class FollowersViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        CircleImageView mIvAvatar;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_location)
        TextView mTvLocation;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_shots_count)
        TextView mTvShotsCount;
        @BindView(R.id.tv_bio)
        TextView mTvBio;
        public FollowersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
