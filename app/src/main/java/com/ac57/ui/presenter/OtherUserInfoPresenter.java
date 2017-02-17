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

    public OtherUserInfoPresenter(IOtherUserInfoView model) {
        super(model);
    }

    public void getOtherUserInfoData(String show_user_id) {
        model.showDailog("");

        UserRepository.getInstance().getOtherUserInfoData(show_user_id).subscribe(new DefaultSubscriber<OtherUserInfoEntity>() {
            @Override
            public void _onNext(OtherUserInfoEntity entity) {
                model.disDailog();
                model.getOtherUserInfoData(entity);

            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError(e);
            }
        });
    }

}
