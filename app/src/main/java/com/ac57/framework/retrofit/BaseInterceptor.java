package com.ac57.framework.retrofit;

import android.util.Log;

import com.ac57.framework.utils.sign.ProUntil;
import com.ac57.framework.utils.sign.ProjectUntil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Du_Li on 2016/12/17.
 */

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String device_id = ProjectUntil.toMd5String(ProUntil.getUuid());
        Log.e("tag", "网络请求的ID  " + ProUntil.getUserid());
        HttpUrl url = original.url().newBuilder()
                .addQueryParameter("client_info", ProjectUntil.getClientInfo())
                .addQueryParameter("device_id", device_id)
                .addQueryParameter("user_id", ProUntil.getUserid())
                .addQueryParameter("platform", "android")
                .build();
//添加请求头
        Request request = original.newBuilder()
//                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .addHeader("Connection", "keep-alive")
                .method(original.method(), original.body())
                .url(url)
                .build();

        return chain.proceed(request);
    }
}