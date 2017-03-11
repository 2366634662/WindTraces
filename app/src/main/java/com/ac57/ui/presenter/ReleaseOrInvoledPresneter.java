package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.ReleaseOrInvoledEntity;
import com.ac57.ui.presenter.view.IReleaseOrInvoledView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public class ReleaseOrInvoledPresneter extends BasePresenter<IReleaseOrInvoledView> {

    public void getReleaseOrInvoledListDat(int page, String is_mine, String show_user_id) {
        UserRepository.getInstance().getReleaseOrInvoledListData(page, is_mine, show_user_id).subscribe(new DefaultSubscriber<List<ReleaseOrInvoledEntity>>() {
            @Override
            public void _onNext(List<ReleaseOrInvoledEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getReleaseOrInvoledListData(entity);
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
