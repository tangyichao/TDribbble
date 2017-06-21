package com.tyc.tdribbble.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public class SpUtils {
    private static SpUtils spUtils;
    private  SharedPreferences.Editor editor;
    private SharedPreferences sp;
    private SpUtils(Context context){
        sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        editor=sp.edit();
    }
    public  static SpUtils getSpUtils(Context context){
        if(spUtils==null)
        {
            synchronized (SpUtils.class)
            {
                if(spUtils==null)
                    spUtils=new SpUtils(context);
            }
        }
        return spUtils;
    }
    public void putString(String key,String value){
        editor.putString(key, value);
        editor.apply();
    }
    public String  getString(String key)
    {
     return sp.getString(key,"");
    }
}
