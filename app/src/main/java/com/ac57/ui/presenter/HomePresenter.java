package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.HomeBannerEntity;
import com.ac57.ui.entity.HomeInfoListEntity;
import com.ac57.ui.presenter.view.IHomeView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 */

public class HomePresenter extends BasePresenter<IHomeView> {

    public void getHomeBannerData() {
        UserRepository.getInstance().getHomeBannerData().subscribe(new DefaultSubscriber<List<HomeBannerEntity>>() {

            @Override
            public void _onNext(List<HomeBannerEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getHomeBannerData(entity);

                }
            }

            @Override
            public void _onError(String e) {
                if (getView() != null)
                    getView().error(e);
            }
        });
    }

    public void getHomeInfoListData(String art_type, int page) {
        UserRepository.getInstance().getHomeInfoListData(art_type, page + "").subscribe(new DefaultSubscriber<List<HomeInfoListEntity>>() {

            @Override
            public void _onNext(List<HomeInfoListEntity> entity) {
                if (getView() != null) {
                    getView().getHomeInfoData(entity);
                    getView().content();
                }

            }

            @Override
            public void _onError(String e) {
                if (getView() != null)
                    getView().error(e);
            }
        });
    }

}
