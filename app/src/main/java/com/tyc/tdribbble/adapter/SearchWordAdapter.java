package com.tyc.tdribbble.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyc.tdribbble.R;
import com.tyc.tdribbble.db.entity.HistoryEntity;
import com.tyc.tdribbble.ui.search.SearchActivity;
import com.tyc.tdribbble.ui.shotsdetails.ShotsDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：tangyc on 2017/6/26
 * 邮箱：874500641@qq.com
 */
public class SearchWordAdapter extends RecyclerView.Adapter<SearchWordAdapter.ColorsViewHolder> {


    private Context context;
    private List<HistoryEntity> list;


    private OnItemClickListener onItemClickListener;

    public SearchWordAdapter(Context context, List<HistoryEntity> list) {
        this.context = context;
        this.list = list;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_word, parent, false);
        return new ColorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ColorsViewHolder holder, int position) {
        //holder.mIvColor.setImageResource(listColor.get(position));
        holder.mTvWord.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(list.get(holder.getAdapterPosition()).getName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<HistoryEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public class ColorsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_word)
        TextView mTvWord;

        public ColorsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        public void onClick(String data);
    }


}
