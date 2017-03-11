package com.ac57.ui.presenter;

import com.ac57.framework.base.BasePresenter;
import com.ac57.framework.retrofit.DefaultSubscriber;
import com.ac57.ui.entity.OptionWatchBottomEntity;
import com.ac57.ui.entity.OptionWatchCenterEntity;
import com.ac57.ui.entity.OptionWatchTopEntity;
import com.ac57.ui.presenter.view.IOptionWatchView;
import com.ac57.ui.service.UserRepository;

import java.util.List;

/**
 */

public class OptionWatchPresenter extends BasePresenter<IOptionWatchView> {

    public void getOptionWatchTopData() {
        UserRepository.getInstance().getOptionWatchTopData().subscribe(new DefaultSubscriber<OptionWatchTopEntity>() {
            @Override
            public void _onNext(OptionWatchTopEntity entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getOptionWatchTopData(entity);
                }
            }

            @Override
            public void _onError(String e) {
                if (getView() != null)
                    getView().error(e);
            }
        });
    }

    public void getOptionWatchCenterData() {
        UserRepository.getInstance().getOptionWatchCenterData().subscribe(new DefaultSubscriber<List<OptionWatchCenterEntity>>() {
            @Override
            public void _onNext(List<OptionWatchCenterEntity> entity) {
                if (getView() != null)
                    getView().getOptionWatchCenterData(entity);
            }

            @Override
            public void _onError(String e) {
                if (getView() != null)
                    getView().error(e);
            }
        });

    }

    public void getOptionWatchBottomData(int page) {
        UserRepository.getInstance().getOptionWatchBottomData(page + "").subscribe(new DefaultSubscriber<List<OptionWatchBottomEntity>>() {
            @Override
            public void _onNext(List<OptionWatchBottomEntity> entity) {
                if (getView() != null) {
                    getView().content();
                    getView().getOptionWatchBottomData(entity);
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
