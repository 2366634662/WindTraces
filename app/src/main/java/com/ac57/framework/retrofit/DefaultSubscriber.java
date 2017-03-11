package com.ac57.framework.retrofit;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


public abstract class DefaultSubscriber<T> extends Subscriber<T> {

    public void onCompleted() {
    }

    public void onError(Throwable e) {
        String reason = "";
//        if (e instanceof AccountsException) {//账户异常
//            reason = "账户异常";
//            Toast.makeText("登录失效，请重新登录");
//        } else
        if (e instanceof JsonSyntaxException) {//数据格式化错误
            reason = "数据格式化错误";
        } else if (e instanceof HttpException) {// http异常
            reason = "http异常";
        } else if (e instanceof UnknownHostException || e instanceof ConnectException) {//未连接网络或DNS错误
            reason = "未连接网络或DNS错误";
        } else if (e instanceof NetworkErrorException) {
            reason = "网络错误";
        } else if (e instanceof SocketException) {
            reason = "连接超时";
        } else if (e instanceof DefaultErrorException) {
            reason = e.toString();
        } else {
            reason = "其他错误";
        }
        Log.e("error", reason);
        _onError(reason);
    }

    public void onNext(T entity) {
        _onNext(entity);
    }


    public abstract void _onNext(T entity);

    public abstract void _onError(String e);


}