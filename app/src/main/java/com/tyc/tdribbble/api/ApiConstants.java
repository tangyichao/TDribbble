package com.tyc.tdribbble.api;

/**
 * 作者：tangyc on 2017/6/20
 * 邮箱：874500641@qq.com
 */
public class ApiConstants {
    public static final String BASE_URL= "https://dribbble.com/";
    public static final String AUTHORIZE="oauth/authorize";
    public static final String TOKEN="oauth/token";


    public static final String BASE_URL_V1= "https://api.dribbble.com/v1/";
    public static final String USER= "user";
    public static final String SHOTS="shots";
    public static final String ONESHOTS = "shots/{shotId}";
    public static final String SEARCH = "search";
    public static final String FOLLOWERS = "users/{userId}/followers?";
    public static final String USERSHOTS = "users/{userId}/shots";
    public static final String USERLIKES = "users/{userId}/likes";
    public static final String COMMENTS = "shots/{shotId}/comments";
    public static final String ATTACHMENTS = "shots/{shotId}/attachments";
    public static final String LIKECOMMENT = "shots/{shotId}/comments/{commentId}/like";
    public static final String LIKESHOT = "shots/{shotId}/like";
    public static final String USERID = "userId";

    public static final String SHOTID = "shotId";
    public static final String LIKES = "userId";
    public static final String COMMENTID = "commentId";
    public static final String PAGE = "page";
    public static final String PERPAGE = "per_page";
    public static final String DATE = "date";
    public static final String BODY = "body";

    public interface Shots {
      /**
       * 类型，默认返回所有类型
       */
       String[] LIST_VALUES = {"", "teams", "debuts", "playoffs", "rebounds", "animated"};

      /**
       * 排序，默认返回综合排序
       */
      String[] SORT_VALUES = {"", "recent", "views", "comments"};

      /**
       * 时间段，默认返回最新
       */
      String[] TIME_VALUES = {"", "week", "month", "year", "ever"};
  }
   public interface OAuthKey{
       String CLIENT_ID = "client_id";
       String CLIENT_SECRET = "client_secret";
       String REDIRECT_URI = "redirect_uri";
       String SCOPE = "scope";
       String STATE = "state";
       String CODE = "code";

       String TOKEN="token";

   }
   public interface OAuth{
        String CLIENT_ID = "ad196f1170dea2fe1a821fdc03da6aaef8221c940bcae9e05b51ad9320699e7f";

        String CLIENT_SECRET = "bd30f7ee220bcc73a6058114d19547c3f3452469227a3594520c5ff8b27dcd19";

       String TOKEN = "a052d7b929180147a8d477aebdf82bb60988660cbec9908b690cc6f6b77d5cfc";

        String REDIRECT_URI = "http://weibo.com/tyc10086/home?wvr=5";

        String SCOPE = "public+write+comment+upload";

        String STATE = "tangyc";

    }


}
