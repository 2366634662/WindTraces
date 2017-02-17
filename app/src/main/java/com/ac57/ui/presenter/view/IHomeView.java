package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.HomeBannerEntity;
import com.ac57.ui.entity.HomeInfoListEntity;

import java.util.List;

/**
 */

public interface IHomeView extends BaseViewController {

    void getHomeBannerData(List<HomeBannerEntity> entity);

    void getHomeInfoData(List<HomeInfoListEntity> entity);

}
