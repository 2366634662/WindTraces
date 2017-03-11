package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.UserInfoDetailEntity;
import com.ac57.ui.presenter.view.ISelfView;
import com.ac57.ui.service.UserRepository;

/**
 */

public class SelfPresenter extends BasePresenter<ISelfView> {

    public void getUserDetailData() {
        UserRepository.getInstance().getUserDetailData().subscribe(new DefaultSubscriber<UserInfoDetailEntity>() {
            @Override
            public void _onNext(UserInfoDetailEntity entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getUserInfoDetail(entity);
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
