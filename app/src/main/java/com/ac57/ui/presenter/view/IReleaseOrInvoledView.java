package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.ReleaseOrInvoledEntity;

import java.util.List;

/**
 * Created on 2017/2/16.
 * Desc :
 */

public interface IReleaseOrInvoledView extends IBaseStatusView {

    void getReleaseOrInvoledListData(List<ReleaseOrInvoledEntity> entities);

}
