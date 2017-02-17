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
    public OptionWatchPresenter(IOptionWatchView model) {
        super(model);
    }

    public void getOptionWatchTopData() {
        model.showDailog("加载中");
        UserRepository.getInstance().getOptionWatchTopData().subscribe(new DefaultSubscriber<OptionWatchTopEntity>() {
            @Override
            public void _onNext(OptionWatchTopEntity entity) {
                model.disDailog();
                model.getOptionWatchTopData(entity);
                getOptionWatchCenterData();
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError("加载错误");
            }
        });
    }

    public void getOptionWatchCenterData() {
        model.showDailog("加载中");
        UserRepository.getInstance().getOptionWatchCenterData().subscribe(new DefaultSubscriber<List<OptionWatchCenterEntity>>() {
            @Override
            public void _onNext(List<OptionWatchCenterEntity> entity) {
                model.disDailog();
                model.getOptionWatchCenterData(entity);
                getOptionWatchBottomData(1);
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError(e.toString());
            }
        });

    }

    public void getOptionWatchBottomData(int page) {
        model.showDailog("加载中");
        UserRepository.getInstance().getOptionWatchBottomData(page + "").subscribe(new DefaultSubscriber<List<OptionWatchBottomEntity>>() {
            @Override
            public void _onNext(List<OptionWatchBottomEntity> entity) {
                model.disDailog();
                model.getOptionWatchBottomData(entity);
            }

            @Override
            public void _onError(String e) {
                model.disDailog();
                model.showError("加载错误");
            }
        });
    }

}
