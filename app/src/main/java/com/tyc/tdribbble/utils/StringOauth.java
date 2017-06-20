package com.tyc.tdribbble.utils;

import com.tyc.tdribbble.api.ApiConstants;

/**
 * 作者：tangyc on 2017/6/20
 * 邮箱：874500641@163.com
 */
public class StringOauth {
    public static String getOauthSting(){
        StringBuffer sb=new StringBuffer();
        sb.append(ApiConstants.BASE_URL)
                .append(ApiConstants.AUTHORIZE)
                .append("?")
                .append(ApiConstants.OAuthKey.CLIENT_ID+"="+ApiConstants.OAuth.CLIENT_ID)
                .append("&&")
                .append(ApiConstants.OAuthKey.REDIRECT_URI+"="+ApiConstants.OAuth.REDIRECT_URI)
                .append("&&")
                .append(ApiConstants.OAuthKey.SCOPE+"="+ApiConstants.OAuth.SCOPE)
                .append("&&")
                .append(ApiConstants.OAuthKey.STATE+"="+ApiConstants.OAuth.SCOPE);
        return sb.toString();
    }
}
