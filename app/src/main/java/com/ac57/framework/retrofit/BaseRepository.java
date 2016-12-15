package com.ac57.framework.retrofit;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.ac57.framework.base.BaseEntity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 封装网络请求，并解析最基本的  BaseEntity
 * 通过请求的code来判断是否网络请求成功
 * 这个就是当code = 200的时候代表网络请求成功
 * 将网络请求到的数据通过 Observable.just(result.datas); 解析
 */
public abstract class BaseRepository {

    protected <T> Observable<T> transform(Observable<BaseEntity<T>> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseEntity<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseEntity<T> result) {

                        Log.e("tag", "请求到的数据" + result.toString());

                        if (result == null) {
                            return Observable.error(new NetworkErrorException());
                        } else if (result.code == 200) {
                            return Observable.just(result.datas);
                        } else if (result.code == 210) {
                            return Observable.error(new DefaultErrorException("存在逻辑错误(mark:描述问题)"));
                        } else if (result.code == 220) {
                            return Observable.error(new DefaultErrorException("token无效"));
                        } else if (result.code == 300) {
                            return Observable.error(new DefaultErrorException("缺少参数（mark:描述问题）"));
                        } else {
                            return Observable.error(new DefaultErrorException("权限不足"));
                        }
                    }

                });
    }

}