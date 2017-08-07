package com.tyc.tdribbble.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.api.ApiConstants;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.ui.search.SearchActivity;
import com.tyc.tdribbble.ui.shotsdetails.ShotsDetailsActivity;
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
public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder> {


    private Context context;
    private List<Integer> listColor;

    public ColorsAdapter(Context context, List<Integer> listColor) {
        this.context = context;
        this.listColor = listColor;
    }


    @Override
    public ColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_colors, parent, false);
        return new ColorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ColorsViewHolder holder, int position) {
        Log.i("debug", listColor.get(position) + "");
        //holder.mIvColor.setImageResource(listColor.get(position));
        Drawable drawable = new ColorDrawable(listColor.get(position));
        holder.mIvColor.setImageDrawable(drawable);
        holder.mTvColor.setText("#" + String.valueOf(Integer.toHexString(listColor.get(position)).toUpperCase()));
        holder.mTvColor.setTextColor(listColor.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, SearchActivity.class);
                intent.putExtra("search", String.valueOf(Integer.toHexString(listColor.get(holder.getAdapterPosition())).toUpperCase()));
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((ShotsDetailsActivity) context).toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listColor.size();
    }


    public class ColorsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_color)
        ImageView mIvColor;
        @BindView(R.id.tv_color)
        TextView mTvColor;

        public ColorsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
