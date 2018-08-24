package com.example.a1.zhoumin2080823.util;

import com.example.a1.zhoumin2080823.api.MyApi;
import com.example.a1.zhoumin2080823.service.GetInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static final int OUTTIME = 5;
    private static RetrofitUtils retrofitUtils;
    private static GetInterface mGetInterface;
    private static Retrofit retrofit;


    public static RetrofitUtils getInstance() {
        if (retrofitUtils==null){
            retrofitUtils=new RetrofitUtils();
        }
        return retrofitUtils;
    }

    private RetrofitUtils() {
    }

    public GetInterface getInterface(){
        return mGetInterface==null?configRetrofit(GetInterface.class):mGetInterface;
    }

    private <T> T configRetrofit(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.BASE)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

    private OkHttpClient configClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(OUTTIME, TimeUnit.SECONDS);
        return builder.build();
    }

}
