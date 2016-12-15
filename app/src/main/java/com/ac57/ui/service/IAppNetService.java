package com.ac57.ui.service;


/**
 * Created by Du_Li on 2016/10/12.
 * 写这个接口  里面添加如下方法  再结合RetrofitHelp  就相当于请求网络数据的过程
 * 剩下的就是UserRepository的事    来将网络请求到的数据进行解析
 */
public interface IAppNetService {

//    /**
//     * UrlUtils.HOME_BANNER_LIST   =   最基本的地址  后面要拼接的请求地址
//     *
//     * @param class_id
//     * @param listtype
//     * @param city     请求参数
//     * @return
//     */
//    @POST(UrlUtils.HOME_BANNER_LIST)
//    Observable<BaseEntity<HomeBannerEntity>> getHomeBannerList(@Query("class_id") int class_id, @Query("listtype") int listtype, @Query("city") String city);
//
//    /**
//     * 使用RxJava的观察者模式，网络请求后直接将数据解析出来
//     *
//     * @return
//     */
//    @POST(UrlUtils.URL_CLASSIFY)
//    Observable<BaseEntity<List<HomeListEntity>>> getHomeList();
//
//    @POST(UrlUtils.URL_HOTSTORE)
//    @FormUrlEncoded
//        //post请求时  有请求参数   就必须加这个注解 不然报错
//    Observable<BaseEntity<HotStoreEntity>> getHotStore(@Field("city") String cityName, @Field("num") int num);
}