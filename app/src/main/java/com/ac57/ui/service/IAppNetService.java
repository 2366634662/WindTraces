package com.ac57.ui.service;


import com.ac57.framework.base.BaseEntity;
import com.ac57.ui.entity.CoustomCollectionEntity;
import com.ac57.ui.entity.DeleteCoustomCollectionData;
import com.ac57.ui.entity.HomeBannerEntity;
import com.ac57.ui.entity.HomeInfoListEntity;
import com.ac57.ui.entity.InteractEventEntity;
import com.ac57.ui.entity.NewGoodsEntity;
import com.ac57.ui.entity.NoticeEntity;
import com.ac57.ui.entity.OptionWatchBottomEntity;
import com.ac57.ui.entity.OptionWatchCenterEntity;
import com.ac57.ui.entity.OptionWatchTopEntity;
import com.ac57.ui.entity.OtherTypesInteractEntity;
import com.ac57.ui.entity.UserInfoData;
import com.ac57.ui.entity.UserInfoDetailEntity;
import com.ac57.ui.utils.UrlUtils;

import java.util.List;

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

    //第三方登陆用户信息
    @POST(UrlUtils.THREE_LOGIN_INFO_URL)
    @FormUrlEncoded
    Observable<BaseEntity<UserInfoData>> getThreeLoginData(@Field("login_type") String login_type, @Field("name") String name,
                                                           @Field("icon") String icon, @Field("uid") String uid,
                                                           @Field("gender") String gender);

    //第三方登陆用户信息
    @POST(UrlUtils.VISITOR_LOGIN)
    @FormUrlEncoded
    Observable<BaseEntity<UserInfoData>> getVisitorLoginData(@Field("only_sign") String only_sign);

    //首页咨询顶部轮播图
    @POST(UrlUtils.HOME_BANNER)
    Observable<BaseEntity<List<HomeBannerEntity>>> getHomeBannerData();

    //首页咨询内容
    @POST(UrlUtils.HOME_INFO_LIST_DATA)
    @FormUrlEncoded
    Observable<BaseEntity<List<HomeInfoListEntity>>> getHomeInfoListData(@Field("art_type") String art_type, @Field("page") String page, @Field("pagesize") String pageSize);

    //互动活动专区
    @POST(UrlUtils.INTERACT_EVENT_LIST_URL)
    @FormUrlEncoded
    Observable<BaseEntity<List<InteractEventEntity>>> getInteractEventListData(@Field("page") String page, @Field("pagesize") String pageSize);

    //互动发表的页面
    @POST(UrlUtils.OTHER_TYPE_INTERACT_LIST_URL)
    @FormUrlEncoded
    Observable<BaseEntity<List<OtherTypesInteractEntity>>> getOtherTypesInteractListData(@Field("art_type") String art_type, @Field("page") String page, @Field("pagesize") String pageSize);

    //行情看点顶部列表
    @POST(UrlUtils.OPTION_WATCH_TOP_URL)
    Observable<BaseEntity<OptionWatchTopEntity>> getOptionWatchTopData();

    //行情看点中间列表
    @POST(UrlUtils.OPTION_WATCH_CENTER_URL)
    Observable<BaseEntity<List<OptionWatchCenterEntity>>> getOptionWatchCenterData();

    //行情看点底部列表
    @POST(UrlUtils.OPTION_WATCH_BOTTOM_URL)
    @FormUrlEncoded
    Observable<BaseEntity<List<OptionWatchBottomEntity>>> getOptionWatchBottomData(@Field("page") String page, @Field("pagesize") String pageSize, @Field("my_top") String my_top);

    //行情看点底部列表
    @POST(UrlUtils.NOTICE_LIST_URL)
    @FormUrlEncoded
    Observable<BaseEntity<List<NoticeEntity>>> getAllTypeNoticeData(@Field("page") String page, @Field("pagesize") String pageSize, @Field("exc_id") String exc_id);

    //行情看点底部列表
    @POST(UrlUtils.NEW_GOODS_URL)
    @FormUrlEncoded
    Observable<BaseEntity<List<NewGoodsEntity>>> getNewGoodsData(@Field("page") String page, @Field("pagesize") String pageSize, @Field("exc_id") String exc_id, @Field("new_goods_type") String new_goods_type);
    //自选藏品列表
    @POST(UrlUtils.COUSTOM_COLLECTION_LIST_URL)
    @FormUrlEncoded
    Observable<BaseEntity<List<CoustomCollectionEntity>>> getCoustomCollectionData(@Field("page") String page, @Field("pagesize") String pageSize, @Field("is_desc") String is_desc, @Field("show_user_id") String show_user_id);
    //删除自选藏品列表
    @POST(UrlUtils.DELETE_COUSTOM_COLLECTION_URL)
    @FormUrlEncoded
    Observable<BaseEntity<DeleteCoustomCollectionData>> deleteCoustomCollectionData(@Field("trade_id") String trade_id, @Field("action") String action);
    //删除自选藏品列表
    @POST(UrlUtils.USER_INFO_DETAIL_URL)
    Observable<BaseEntity<UserInfoDetailEntity>> getUserDetailData();

}