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

    public void getSelfMessageData(int page) {
        UserRepository.getInstance().getSelfMessageData(page).subscribe(new DefaultSubscriber<List<SelfMessageEntity>>() {
            @Override
            public void _onNext(List<SelfMessageEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getSelfMessageData(entity);
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
