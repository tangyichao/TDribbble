package com.tyc.tdribbble.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

/**
 * 作者：tangyc on 2017/6/28
 * 邮箱：874500641@qq.com
 */
public class CricleImage extends AppCompatImageView {
    public CricleImage(Context context) {
        super(context);
        Log.i("debug", "CricleImage(Context context)");
    }

    public CricleImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i("debug", "CricleImage(Context context, @Nullable AttributeSet attrs)");
    }

    public CricleImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("debug", "onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("debug", "onDraw");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }
}
