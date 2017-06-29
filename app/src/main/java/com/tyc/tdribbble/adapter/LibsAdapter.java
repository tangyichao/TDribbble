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

import com.bumptech.glide.Glide;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.entity.Library;
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
public class LibsAdapter extends RecyclerView.Adapter<LibsAdapter.LibsViewHolder> {

    private Context context;
    private Library[] libs;

    public LibsAdapter(Context context, Library[] libs) {
        this.context = context;
        this.libs = libs;
    }

    @Override
    public LibsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_libs, parent, false);
        return new LibsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LibsViewHolder holder, int position) {
        holder.mTvName.setText(libs[position].getName());
        holder.mTvDesc.setText(libs[position].getDesc());
        Glide.with(context).load(libs[position].getImageUrl()).into(holder.mTvAvatar);
    }

    @Override
    public int getItemCount() {
        return libs.length;
    }


    public class LibsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        CircleImageView mTvAvatar;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_desc)
        TextView mTvDesc;

        public LibsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
