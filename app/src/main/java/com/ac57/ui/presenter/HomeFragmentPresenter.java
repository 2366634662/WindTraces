package com.ac57.ui.presenter;

import android.app.Activity;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.framework.retrofit.UserRepository;
import com.ac57.ui.entity.HomeBannerEntity;
import com.ac57.ui.entity.HomeInfoListEntity;

import java.util.List;

/**
 * Created by Du_Li on 2016/12/25.
 */

public class HomeFragmentPresenter extends BasePresenter<IHomeViewControll> {

    public HomeFragmentPresenter(IHomeViewControll model, Activity activity) {
        super(model, activity);
    }

    public void getHomeBannerData() {
        model.showDailog("加载中");
        UserRepository.getInstance().getHomeBannerData().subscribe(new DefaultSubscriber<List<HomeBannerEntity>>() {

            @Override
            public void _onNext(List<HomeBannerEntity> entity) {
                model.disDailog();
                model.getHomeBannerData(entity);

            }

            @Override
            public void _onError(String e) {
                model.showError("加载失败");
                model.disDailog();
            }
        });
    }

    public void getHomeInfoListData(String art_type, int page) {
        model.showDailog("加载中");
        UserRepository.getInstance().getHomeInfoListData(art_type, page + "").subscribe(new DefaultSubscriber<List<HomeInfoListEntity>>() {

            @Override
            public void _onNext(List<HomeInfoListEntity> entity) {
                model.getHomeInfoData(entity);
                model.disDailog();
            }

            @Override
            public void _onError(String e) {
                model.showError("加载数据失败");
                model.disDailog();
            }
        });
    }

}
