package com.ac57.ui.service;


import com.ac57.framework.base.BaseEntity;
import com.ac57.ui.entity.UserInfoData;
import com.ac57.ui.utils.UrlUtils;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Du_Li on 2016/10/12.
 * 写这个接口  里面添加如下方法  再结合RetrofitHelp  就相当于请求网络数据的过程
 * 剩下的就是UserRepository的事    来将网络请求到的数据进行解析
 */
public interface IAppNetService {
    //    //用户信息
//    @POST(UrlUtils.USER_INFO_DATA)
//    @FormUrlEncoded
//    //post请求时  有请求参数   就必须加这个注解 不然报错
//    Observable<BaseEntity<UserInfoData>> getUserInfoData(@Field("phone") String phone, @Field("rand") String rand,
//                                                         @Field("login_sign") String login_sign, @Field("client_info") String client_info, @Field("device_id") String device_id,
//                                                         @Field("user_id") String user_id, @Field("platform") String platform);
    //用户信息
    @POST(UrlUtils.USER_INFO_DATA)
    @FormUrlEncoded
    //post请求时  有请求参数   就必须加这个注解 不然报错
    Observable<BaseEntity<UserInfoData>> getUserInfoData(@Field("phone") String phone, @Field("rand") String rand,
                                                         @Field("login_sign") String login_sign);
}