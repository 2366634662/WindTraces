package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.OtherUserInfoEntity;
import com.ac57.ui.presenter.view.IOtherUserInfoView;
import com.ac57.ui.service.UserRepository;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public class OtherUserInfoPresenter extends BasePresenter<IOtherUserInfoView> {


    public void getOtherUserInfoData(String show_user_id) {

        UserRepository.getInstance().getOtherUserInfoData(show_user_id).subscribe(new DefaultSubscriber<OtherUserInfoEntity>() {
            @Override
            public void _onNext(OtherUserInfoEntity entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getOtherUserInfoData(entity);
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
