package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.ExchangeEntity;
import com.ac57.ui.presenter.view.IExchangeView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 * Created on 2017/2/5.
 * Desc :
 */

public class ExchangePresenter extends BasePresenter<IExchangeView> {

    public void getExchangeData() {
        UserRepository.getInstance().getExchangeData(1).subscribe(new DefaultSubscriber<List<ExchangeEntity>>() {
            @Override
            public void _onNext(List<ExchangeEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getExchangeData(entity);
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
