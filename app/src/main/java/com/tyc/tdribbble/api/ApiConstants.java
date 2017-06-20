package com.tyc.tdribbble.api;

/**
 * 作者：tangyc on 2017/6/20
 * 邮箱：874500641@qq.com
 */
public class ApiConstants {
    public static final String BASE_URL= "https://dribbble.com/";
    public static final String AUTHORIZE="oauth/authorize";

   public interface OAuthKey{
       String CLIENT_ID = "client_id";

       String REDIRECT_URI = "redirect_uri";

       String SCOPE = "scope";

       String STATE = "state";
   }
   public interface OAuth{
        String CLIENT_ID = "ad196f1170dea2fe1a821fdc03da6aaef8221c940bcae9e05b51ad9320699e7f";

        String CLIENT_SECRET = "bd30f7ee220bcc73a6058114d19547c3f3452469227a3594520c5ff8b27dcd19";

        String TOKEN = "02d08f10e824ec7ed54636b8f9bc46e23e6e343c9c3d130295103004f0006a65";

        String REDIRECT_URI = "http://weibo.com/tyc10086/home?wvr=5";

        String SCOPE = "public write comment upload";

        String STATE = "tangyc";

//         params.put(ApiConstants.ParamKey.CLIENT_ID, ApiConstants.ParamValue.CLIENT_ID);
//        params.put(ApiConstants.ParamKey.REDIRECT_URI, ApiConstants.ParamValue.REDIRECT_URI);
//        params.put(ApiConstants.ParamKey.SCOPE, ApiConstants.ParamValue.SCOPE);
//        params.put(ApiConstants.ParamKey.STATE, ApiConstants.ParamValue.STATE)
    }
}
