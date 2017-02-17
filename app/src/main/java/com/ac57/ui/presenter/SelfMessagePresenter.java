package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.SelfMessageEntity;
import com.ac57.ui.presenter.view.ISelfMessageView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 * Desc :
 */

public class SelfMessagePresenter extends BasePresenter<ISelfMessageView> {

    public SelfMessagePresenter(ISelfMessageView model) {
        super(model);
    }

    public void getSelfMessageData(int page) {
        model.showDailog("加载中");
        UserRepository.getInstance().getSelfMessageData(page).subscribe(new DefaultSubscriber<List<SelfMessageEntity>>() {
            @Override
            public void _onNext(List<SelfMessageEntity> entity) {
                model.disDailog();
                model.getSelfMessageData(entity);
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError("" + e);
            }
        });
    }

}
