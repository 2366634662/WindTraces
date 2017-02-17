package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.SelectExchangeEntity;

/**
 * Created by Du_Li on 2017/2/4.
 * Desc:
 */

public interface ISelectExchangeView extends BaseViewController {

    void getSelectExchangeData(SelectExchangeEntity entity);

}
