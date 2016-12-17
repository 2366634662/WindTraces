package com.ac57.framework.retrofit;


import com.ac57.ui.service.IAppNetService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Du_Li on 2016/10/12.
 */

/**
 * 封装 Retrofit的网络请求过程  设置一些每次网络请求都要用到的默认参数
 */
public class RetrofitHelp {
    private static String URL_BASE = "http://112.74.106.149/wind/Htdoc/";
    private static volatile Retrofit mRetrofit;
    private static volatile RetrofitHelp mClient;


    private RetrofitHelp() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(5, TimeUnit.SECONDS);
        client.addInterceptor(new BaseInterceptor());
        mRetrofit = new Retrofit.Builder()
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(URL_BASE)
                .build();
    }

    public static RetrofitHelp getIns() {
        if (mClient == null) {
            synchronized (RetrofitHelp.class) {
                if (mClient == null) {
                    mClient = new RetrofitHelp();
                }
            }
        }
        return mClient;
    }

    /**
     * 拿到要请求网络的  Service
     *
     * @param apiService
     * @param <T>
     * @return
     */
    protected static <T> T create(Class<T> apiService) {
        return mClient.getIns().mRetrofit.create(apiService);
    }

    public IAppNetService getAppservice() {
        return create(IAppNetService.class);
    }
}