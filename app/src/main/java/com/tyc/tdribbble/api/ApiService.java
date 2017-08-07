package com.tyc.tdribbble.api;

import com.tyc.tdribbble.entity.AttachmentsEntity;
import com.tyc.tdribbble.entity.CommentsEntity;
import com.tyc.tdribbble.entity.FollowersEntity;
import com.tyc.tdribbble.entity.LikesEntity;
import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.TTEntity;
import com.tyc.tdribbble.entity.TokenEntity;
import com.tyc.tdribbble.entity.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
    Observable<UserEntity> getUser();

    @GET(ApiConstants.SHOTS)
    Observable<List<ShotsEntity>> getShots(@QueryMap Map<String, String> map);

    @GET(ApiConstants.ONESHOTS)
    Observable<ShotsEntity> getShot(@Path(ApiConstants.SHOTID) String shotId);



    @GET(ApiConstants.LIKESHOT)
    Observable<TTEntity> checkLikeShot(@Path(ApiConstants.SHOTID) String shotId);

    @POST(ApiConstants.LIKESHOT)
    Observable<TTEntity> getLikeShot(@Path(ApiConstants.SHOTID) String shotId);

    @DELETE(ApiConstants.LIKESHOT)
    Observable<TTEntity> unLikeShot(@Path(ApiConstants.SHOTID) String shotId);

    @GET(ApiConstants.SEARCH)
    Observable<List<ShotsEntity>> getSearch(@QueryMap Map<String, String> map);

    @GET(ApiConstants.FOLLOWERS + "?" + ApiConstants.PERPAGE + "=20")
    Observable<List<FollowersEntity>> getFollowers(@Path(ApiConstants.USERID) String userId);

    @GET(ApiConstants.USERSHOTS)
    Observable<List<ShotsEntity>> getUserShots(@Path(ApiConstants.USERID) String userId);

    @GET(ApiConstants.USERLIKES)
    Observable<List<LikesEntity>> getUserLikes(@Path(ApiConstants.USERID) String userId);

    @GET(ApiConstants.COMMENTS)
    Observable<List<CommentsEntity>> getComments(@Path(ApiConstants.SHOTID) String shotId, @QueryMap HashMap<String, String> hashMap);

    @POST(ApiConstants.COMMENTS)
    Observable<CommentsEntity> createComments(@Path(ApiConstants.SHOTID) String shotId, @Query(ApiConstants.BODY) String body, @Query(ApiConstants.OAuthKey.SCOPE) String scope);

    @POST(ApiConstants.LIKECOMMENT)
    Observable<TTEntity> getLikeComment(@Path(ApiConstants.SHOTID) String shotId, @Path(ApiConstants.COMMENTID) String commentId);

    @GET(ApiConstants.ATTACHMENTS)
    Observable<List<AttachmentsEntity>> getAttachments(@Path(ApiConstants.SHOTID) String shotId);
}
