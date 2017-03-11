package com.ac57.ui.presenter.view;

import com.ac57.framework.base.IBaseStatusView;
import com.ac57.ui.entity.MyReleaseAndInvoledEntity;

import java.util.List;

/**
 * Created on 2017/2/5.
 * Desc :
 */

public interface IMyReleaseAndInvoledView extends IBaseStatusView {
    void getMyReleaseData(List<MyReleaseAndInvoledEntity> entities);
}
