package com.ac57.ui.presenter.view;

import com.ac57.framework.base.BaseViewController;
import com.ac57.ui.entity.MyReleaseAndInvoledEntity;

import java.util.List;

/**
 * Created on 2017/2/5.
 * Desc :
 */

public interface IMyReleaseAndInvoledView extends BaseViewController {
    void getMyReleaseData(List<MyReleaseAndInvoledEntity> entities);
}
