package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.OtherTypesInteractEntity;
import com.ac57.ui.presenter.view.IOtherTypesInteractView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 */

public class OtherTypesInteractPresenter extends BasePresenter<IOtherTypesInteractView> {

    public void getOtherTypeInteractData(int page, String type) {
        UserRepository.getInstance().getOtherTypesInteractListData("" + page, type).subscribe(new DefaultSubscriber<List<OtherTypesInteractEntity>>() {
            @Override
            public void _onNext(List<OtherTypesInteractEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getOtherTypesInteractDatas(entity);
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
