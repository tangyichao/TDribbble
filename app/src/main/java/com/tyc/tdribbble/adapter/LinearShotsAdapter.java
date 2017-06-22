package com.tyc.tdribbble.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tyc.tdribbble.R;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.utils.DisplayUtils;
import com.tyc.tdribbble.utils.ScreenUtils;
import com.tyc.tdribbble.utils.transform.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int width= ScreenUtils.getScreenWidth(context)- DisplayUtils.dip2px(context,16f);
        if(type==1)
        {
            width=width/2;
        }
        int height=width*3/4;
        if(shotsEntities.get(position).isAnimated()){
            String hidpi=shotsEntities.get(position).getImages().getHidpi();
            Glide.with(context).load(hidpi).asGif().placeholder(R.drawable.bg_linear_shots).override(width,height).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(((LinearShotsViewHolder) holder).mIvShots);
           if(type==0){
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
        public LinearShotsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            initView();
        }
         private void initView() {

             int width = ScreenUtils.getScreenWidth(context) - DisplayUtils.dip2px(context, 16f);
             if(type==1)
             {
                 width=width/2;
             }
                 int height = width * 3 / 4;
                 ViewGroup.LayoutParams params = mIvShots.getLayoutParams();
                 params.height = height;
                 params.width = width;
                 mIvShots.setLayoutParams(params);

         }
     }
}
