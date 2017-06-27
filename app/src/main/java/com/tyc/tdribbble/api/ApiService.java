package com.tyc.tdribbble.api;

import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.TokenEntity;
import com.tyc.tdribbble.entity.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 作者：tangyc on 2017/6/21
 * 邮箱：874500641@qq.com
 */
public interface ApiService {
    @POST(ApiConstants.TOKEN)
    Observable<TokenEntity> getToKen(@Query(ApiConstants.OAuthKey.CLIENT_ID) String clientID,@Query(ApiConstants.OAuthKey.CLIENT_SECRET) String clientSecret,@Query(ApiConstants.OAuthKey.REDIRECT_URI) String redirectUri,@Query(ApiConstants.OAuthKey.CODE) String code);
    @GET(ApiConstants.USER)
    Observable<UserEntity> getUser(@Query(ApiConstants.OAuthKey.TOKEN)String token);
    //@Headers("Authorization: Bearer 9416495d875cf69abfe9d0bd16b0a007dea727d1d0512e102fed64a5009cfd07")
    @GET(ApiConstants.SHOTS)
    Observable<List<ShotsEntity>> getShots(@QueryMap Map<String,String> map,@Header("Authorization") String token);

    @GET(ApiConstants.FOLLOWERS)
    Observable<List<FollowersEntity>> getFollowers(@Path(ApiConstants.USERID) String userId, @Header("Authorization") String token);

    @GET(ApiConstants.USERSHOTS)
    Observable<List<ShotsEntity>> getUserShots(@Path(ApiConstants.USERID) String userId, @Header("Authorization") String token);

    @GET(ApiConstants.COMMENTS)
    Observable<List<CommentsEntity>> getComments(@Path(ApiConstants.SHOTID) String shotId, @QueryMap HashMap<String, String> hashMap, @Header("Authorization") String token);
}
