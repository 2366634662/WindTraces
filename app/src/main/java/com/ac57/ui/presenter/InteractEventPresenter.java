package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.InteractEventEntity;
import com.ac57.ui.presenter.view.IInteractEventView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 */

public class InteractEventPresenter extends BasePresenter<IInteractEventView> {

    public void getInteractEventData(int page) {
        if (getView() != null)
            getView().loading();
        UserRepository.getInstance().getInteractEventListData(page + "").subscribe(new DefaultSubscriber<List<InteractEventEntity>>() {
            @Override
            public void _onNext(List<InteractEventEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getInteractEventData(entity);
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
