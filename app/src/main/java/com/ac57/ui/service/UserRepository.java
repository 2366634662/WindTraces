package com.ac57.ui.service;

import com.ac57.framework.retrofit.BaseRepository;
import com.ac57.framework.retrofit.RetrofitHelp;
import com.ac57.framework.utils.sign.ProUntil;
import com.ac57.framework.utils.sign.ProjectUntil;
import com.ac57.ui.entity.HomeBannerEntity;
import com.ac57.ui.entity.HomeInfoListEntity;
import com.ac57.ui.entity.InteractEventEntity;
import com.ac57.ui.entity.OptionWatchBottomEntity;
import com.ac57.ui.entity.OptionWatchCenterEntity;
import com.ac57.ui.entity.OptionWatchTopEntity;
import com.ac57.ui.entity.OtherTypesInteractEntity;
import com.ac57.ui.entity.UserInfoData;

import java.util.List;

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

    public Observable<UserInfoData> getThreeUserInfoData(String login_type, String name, String icon, String gender, String uid) {
        String uidd = ProjectUntil.toMd5String(uid);
        return transform(RetrofitHelp.getIns().getAppservice().getThreeLoginData(login_type, name, icon, gender, uidd));
    }

    public Observable<UserInfoData> getVisitorUserInfoData() {
        String only_sign = ProjectUntil.toMd5String(ProjectUntil.toMd5String(ProUntil.getUuid()));
        return transform(RetrofitHelp.getIns().getAppservice().getVisitorLoginData(only_sign));
    }

    //
    public Observable<List<HomeBannerEntity>> getHomeBannerData() {
        return transform(RetrofitHelp.getIns().getAppservice().getHomeBannerData());
    }

    public Observable<List<HomeInfoListEntity>> getHomeInfoListData(String art_type, String page) {
        return transform(RetrofitHelp.getIns().getAppservice().getHomeInfoListData(art_type, page, "10"));
    }

    public Observable<List<InteractEventEntity>> getInteractEventListData(String page) {
        return transform(RetrofitHelp.getIns().getAppservice().getInteractEventListData(page, "10"));
    }

    public Observable<List<OtherTypesInteractEntity>> getOtherTypesInteractListData(String page, String art_type) {
        return transform(RetrofitHelp.getIns().getAppservice().getOtherTypesInteractListData(art_type, page, "10"));
    }

    public Observable<OptionWatchTopEntity> getOptionWatchTopData() {
        return transform(RetrofitHelp.getIns().getAppservice().getOptionWatchTopData());
    }

    public Observable<List<OptionWatchCenterEntity>> getOptionWatchCenterData() {
        return transform(RetrofitHelp.getIns().getAppservice().getOptionWatchCenterData());
    }

    public Observable<List<OptionWatchBottomEntity>> getOptionWatchBottomData(String page) {
        return transform(RetrofitHelp.getIns().getAppservice().getOptionWatchBottomData(page, "10", "102"));
    }
}