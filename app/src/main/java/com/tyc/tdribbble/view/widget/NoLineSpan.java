package com.tyc.tdribbble.view.widget;

import android.text.TextPaint;
import android.text.style.UnderlineSpan;

public class NoLineSpan extends UnderlineSpan {

    public static final NoLineSpan CREATOR = new NoLineSpan();

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(false);
    }
}
 