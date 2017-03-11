package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.OptionWatchBottomEntity;
import com.ac57.ui.entity.OptionWatchCenterEntity;
import com.ac57.ui.entity.OptionWatchTopEntity;

import java.util.List;

/**
 */

public interface IOptionWatchView extends IBaseStatusView {

    void getOptionWatchTopData(OptionWatchTopEntity entity);

    void getOptionWatchCenterData(List<OptionWatchCenterEntity> entity);

    void getOptionWatchBottomData(List<OptionWatchBottomEntity> entities);

}
