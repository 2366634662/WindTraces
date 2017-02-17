package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.MyReleaseAndInvoledEntity;
import com.ac57.ui.presenter.view.IMyReleaseAndInvoledView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 * Created on 2017/2/5.
 * Desc :
 */

public class MyReleaseAndInvoledPresenter extends BasePresenter<IMyReleaseAndInvoledView> {
    public MyReleaseAndInvoledPresenter(IMyReleaseAndInvoledView model) {
        super(model);
    }

    public void getMyReleaseData(int page, String type, String id) {
        model.showDailog("");
        UserRepository.getInstance().getMyReleaseData(page, type, id).subscribe(new DefaultSubscriber<List<MyReleaseAndInvoledEntity>>() {
            @Override
            public void _onNext(List<MyReleaseAndInvoledEntity> entity) {
                model.disDailog();
                model.getMyReleaseData(entity);
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError(e);
            }
        });
    }

}
