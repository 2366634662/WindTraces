package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.SelectExchangeEntity;
import com.ac57.ui.presenter.view.ISelectExchangeView;
import com.ac57.ui.service.UserRepository;

/**
 * Created by Du_Li on 2017/2/4.
 * Desc:
 */

public class SelectExchangePresenter extends BasePresenter<ISelectExchangeView> {


    public void getSelectExchangeData() {
        UserRepository.getInstance().getSelectExchangeData().subscribe(new DefaultSubscriber<SelectExchangeEntity>() {
            @Override
            public void _onNext(SelectExchangeEntity entity) {
                if (getView() != null){
                    getView().content();
                getView().getSelectExchangeData(entity);
            }}

            @Override
            public void _onError(String e) {
                if (getView() != null)
                    getView().error(e);
            }
        });
    }
}
