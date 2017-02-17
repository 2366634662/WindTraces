package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.UserInfoDetailEntity;
import com.ac57.ui.presenter.view.ISelfView;
import com.ac57.ui.service.UserRepository;

/**
 */

public class SelfPresenter extends BasePresenter<ISelfView> {
    public SelfPresenter(ISelfView model) {
        super(model);
    }

    public void getUserDetailData() {
        model.showDailog("获取个人信息中");
        UserRepository.getInstance().getUserDetailData().subscribe(new DefaultSubscriber<UserInfoDetailEntity>() {
            @Override
            public void _onNext(UserInfoDetailEntity entity) {
                model.disDailog();
                model.getUserInfoDetail(entity);
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError("" + e);
            }
        });
    }
}
