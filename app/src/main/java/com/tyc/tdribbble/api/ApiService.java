package com.tyc.tdribbble.api;

import com.tyc.tdribbble.entity.TokenEntity;
import com.tyc.tdribbble.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    @GET(ApiConstants.SHOTS)
    Observable<UserEntity> getShots(@QueryMap String map);

}
