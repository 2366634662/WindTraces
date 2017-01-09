package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.OptionWatchBottomEntity;
import com.ac57.ui.entity.OptionWatchCenterEntity;
import com.ac57.ui.entity.OptionWatchTopEntity;

import java.util.List;

/**
 * Created by Du_Li on 2016/12/31.
 */

public interface IOptionWatchView extends BaseViewController {

    void getOptionWatchTopData(OptionWatchTopEntity entity);

    void getOptionWatchCenterData(List<OptionWatchCenterEntity> entity);

    void getOptionWatchBottomData(List<OptionWatchBottomEntity> entities);

}
