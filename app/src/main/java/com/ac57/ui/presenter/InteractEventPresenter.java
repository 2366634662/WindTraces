package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.service.UserRepository;
import com.ac57.ui.entity.InteractEventEntity;
import com.ac57.ui.presenter.view.IInteractEventView;

import java.util.List;

/**
 */

public class InteractEventPresenter extends BasePresenter<IInteractEventView> {
    public InteractEventPresenter(IInteractEventView model) {
        super(model);
    }

    public void getInteractEventData(int page) {
        model.showDailog("");
        UserRepository.getInstance().getInteractEventListData(page + "").subscribe(new DefaultSubscriber<List<InteractEventEntity>>() {
            @Override
            public void _onNext(List<InteractEventEntity> entity) {
                model.disDailog();
                model.getInteractEventData(entity);
            }

            @Override
            public void _onError(String e) {
                model.showError("获取数据失败");
                model.disDailog();
            }
        });
    }

}
