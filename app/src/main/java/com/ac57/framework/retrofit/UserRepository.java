package com.ac57.framework.retrofit;

import com.ac57.ui.entity.UserInfoData;

import rx.Observable;

/**
 * Created by Du_Li on 2016/10/12.
 * 继承了BaseRepository  通过下面的方法  进行网络请求  再将网络请求到的数据进行回调
 */

public class UserRepository extends BaseRepository {

    private static volatile UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) ;
        try {
            if (instance == null)
                instance = new UserRepository();
            return instance;
        } finally {
        }
    }

    public Observable<UserInfoData> getUserInfoData(String phone, String rand, String login_sign) {
        return transform(RetrofitHelp.getIns().getAppservice().getUserInfoData(phone, rand, login_sign));
    }
//
//    public Observable<List<HomeListEntity>> getHomeList() {
//        return transform(RetrofitHelp.getIns().getHomeService().getHomeList());
//    }
//
//    public Observable<HotStoreEntity> getHotStoreList(String cityName, int num) {
//        return transform(RetrofitHelp.getIns().getHomeService().getHotStore(cityName, num));
//    }
}