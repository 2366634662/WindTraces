package com.ac57.framework.retrofit;

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

//    public Observable<HomeBannerEntity> getHomeBannerDatas(int class_id, int listtype, String city) {
//        return transform(RetrofitHelp.getIns().getHomeService().getHomeBannerList(class_id, listtype, city));
//    }
//
//    public Observable<List<HomeListEntity>> getHomeList() {
//        return transform(RetrofitHelp.getIns().getHomeService().getHomeList());
//    }
//
//    public Observable<HotStoreEntity> getHotStoreList(String cityName, int num) {
//        return transform(RetrofitHelp.getIns().getHomeService().getHotStore(cityName, num));
//    }
}