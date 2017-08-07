package com.tyc.tdribbble.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

public final class ThemeUtils {

    private ThemeUtils() {
    }

    public static boolean configThemeBeforeOnCreate(@NonNull Activity activity, @StyleRes int dark, @StyleRes int light) {
        boolean enable = SpUtils.getSpUtils(activity).getBoolean("dark_theme");//默认true
        activity.setTheme(enable ? dark : light);
        return enable;
    }

    public static void notifyThemeApply(@NonNull final Activity activity) {
        HandlerUtils.handler.post(new Runnable() {

            @Override
            public void run() {
                activity.recreate();
            }

        });
    }

}