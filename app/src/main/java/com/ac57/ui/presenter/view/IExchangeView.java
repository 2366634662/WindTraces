package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.ExchangeEntity;

import java.util.List;

/**
 * Created on 2017/2/5.
 * Desc :
 */

public interface IExchangeView extends IBaseStatusView {
    void getExchangeData(List<ExchangeEntity> entities);
}
