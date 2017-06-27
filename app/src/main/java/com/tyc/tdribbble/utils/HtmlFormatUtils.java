package com.tyc.tdribbble.utils;

import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.tyc.tdribbble.view.widget.NoLineSpan;


public class HtmlFormatUtils {

    public static void Html2StringNoP(TextView textView, String htmlStr) {
        htmlStr = htmlStr.replace("<p>", "");
        htmlStr = htmlStr.replace("</p>", "");
        Html2String(textView, htmlStr);
    }

    public static void Html2String(TextView textView, String htmlStr) {
        textView.setText(Html.fromHtml(htmlStr));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Spannable spannable = (Spannable) textView.getText();
        NoLineSpan noLineSpan = new NoLineSpan();
        spannable.setSpan(noLineSpan, 0, spannable.length(), Spanned.SPAN_MARK_MARK);
    }

    public static String setupBold(String text, String normalText) {
        return Html.fromHtml("<b>" + text + "</b> " + normalText).toString();
    }

    public static String setupBold(int text, String normalText) {
        return setupBold(text + "", normalText);
    }
}