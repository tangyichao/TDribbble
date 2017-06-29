package com.tyc.tdribbble.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：tangyc on 2017/6/20
 * 邮箱：874500641@qq.com
 */
public class ApiManager {
    public static Retrofit getRetrofitJsoup(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(20, TimeUnit.SECONDS).build();
        return new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new DribbbleSearchConverter.Factory())
                .client(client).build();
    }
    public static Retrofit getRetrofit(String url){
        OkHttpClient client=new OkHttpClient().newBuilder().readTimeout(20, TimeUnit.SECONDS).build();
        return  new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
    }
    public static Retrofit getRetrofitUser(String url, final String token){
        OkHttpClient client=new OkHttpClient().newBuilder().readTimeout(20, TimeUnit.SECONDS).authenticator(new Authenticator(){

            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                return response.request().newBuilder()
                        .header("Authorization","Bearer "+token)
                        .build();
            }
        }).build();
        return  new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
    }
    public static Retrofit getRetrofitShots(String url){
        OkHttpClient client=new OkHttpClient().newBuilder().readTimeout(20, TimeUnit.SECONDS).build();
        return  new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
    }
}
